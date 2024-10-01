document.addEventListener('DOMContentLoaded', async function () {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    const pagina = params.get('pagina');


    function loadServicos(pagina) {
        fetch('api/servicos?pagina=' + (pagina - 1))
            .then(response => response.json())
            .then(servicos => {
                var accordionServicos = document.getElementById("accordionServicos");
                accordionServicos.innerHTML = "";

                servicos.content.forEach((servico, index) => {
                    var accordionItem = document.createElement("div");
                    accordionItem.className = "accordion-item";

                    accordionItem.innerHTML = `
                    <h2 class="accordion-header" id="heading${index}">
                        <button class="accordion-button collapsed d-flex align-items-center" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${index}" aria-expanded="false" aria-controls="collapse${index}">
                            <i class="fa-solid fa-briefcase me-2"></i> 
                            <span class="fw-bold">${servico.nome}</span> - R$ ${servico.preco.toFixed(2)}
                        </button>
                    </h2>
                    <div id="collapse${index}" class="accordion-collapse collapse" aria-labelledby="heading${index}" data-bs-parent="#accordionServicos">
                        <div class="accordion-body bg-light">
                            <div class="row">
                                <div class="col-md-6">
                                    <ul class="list-group">
                                        <li class="list-group-item mb-2"><strong>ID:</strong> ${servico.id}</li>
                                        <li class="list-group-item mb-2"><strong>Nome do Serviço:</strong> ${servico.nome}</li>
                                        <li class="list-group-item mb-2"><strong>Status:</strong> ${servico.status}</li>
                                    </ul>
                                </div>
                                <div class="col-md-6">
                                    <ul class="list-group">
                                        <li class="list-group-item mb-2"><strong>Pagamento Previsto:</strong> ${formatDate(servico.pagamentoPrevisto)}</li>
                                        <li class="list-group-item mb-2"><strong>Pagamento Final:</strong> ${formatDate(servico.pagamentoFinal)}</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="d-flex justify-content-end mt-3">
                                <a class="btn btn-outline-info btn-sm mx-1" onclick="window.location.href = '/visualizar_servico?id=${servico.id}'; return false;">
                                    <i class="fa-solid fa-eye"></i> Visualizar
                                </a>
                                <a class="btn btn-outline-success btn-sm mx-1" onclick="if (confirm('Deseja realmente marcar o serviço como concluído?')) { atualizarStatusServico(${servico.id}, 'CONCLUIDO'); } return false;">
                                    <i class="fa-solid fa-check"></i> Concluir
                                </a>
                                <a class="btn btn-outline-warning btn-sm mx-1" onclick="if (confirm('Deseja realmente cancelar o serviço?')) { atualizarStatusServico(${servico.id}, 'CANCELADO'); } return false;">
                                    <i class="fa-solid fa-ban"></i> Cancelar
                                </a>
                            </div>
                        </div>
                    </div>
                    `;

                    accordionServicos.appendChild(accordionItem);
                });
            })
            .catch(error => {
                alert('Erro na requisição:', error);
            });
    }

    loadServicos(pagina);
});

function atualizarStatusServico(id, novoStatus) {
    fetch(`/api/servicos/${id}/atualizar?status=${novoStatus}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ status: novoStatus })
    })
        .then(response => {
            if (response.ok) {
                alert(`Serviço ${novoStatus === 'CONCLUIDO' ? 'concluído' : 'cancelado'} com sucesso!`);
                location.reload();
            } else {
                alert(`Falha ao ${novoStatus === 'CONCLUIDO' ? 'concluir' : 'cancelar'} o serviço.`);
            }
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}

function formatDate(dateString) {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR', { timeZone: 'UTC' });
}

async function porcentagem() {
    const ctx = document.getElementById('myChart');
    fetch('api/servicos/porcentagemStatusServicoTotal?status=2')
        .then(response => response.json())
        .then(servico => {
            let totalStatus = Object.keys(servico).find(key => key === 'totalStatus');
            let totalServicos = Object.keys(servico).find(key => key === 'totalServicos');

            new Chart(ctx, {
                type: 'doughnut',
                data: {
                    labels: ["EM ANDAMENTO", "SERVIÇOS"],
                    datasets: [{
                        label: ["Total"],
                        data: [servico.totalStatus, servico.totalServicos - servico.totalStatus],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    label: "Total:",
                    responsive: true,
                    plugins: {
                        tooltip: {
                            enabled: false
                        },
                        legend: {
                            display: true,
                            position: 'bottom',
                            onClick: (e) => e.stopPropagation() 
                        }
                    }
                },
                plugins: [{
                    beforeDraw: function (chart) {
                        const width = chart.width,
                            height = chart.height,
                            ctx = chart.ctx;
                        console.log(servico)
                        ctx.restore();
                        const fontSize = (height / 120).toFixed(2);
                        ctx.font = `${fontSize}em sans-serif`; 
                        ctx.fontWeight = '500';

                        const text = servico.porcentagem + "%",
                            textX = Math.round((width - ctx.measureText(text).width) / 2) + 8,
                            textY = height / 2 - 10;

                        ctx.fillText(text, textX, textY);
                        ctx.save();
                    }
                }]
            });
        })
        .catch(error => {
            alert('Erro na requisição:', error);
        });
}
porcentagem();
