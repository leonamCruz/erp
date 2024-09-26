document.addEventListener('DOMContentLoaded', async function () {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    const pagina = params.get('pagina');

    function loadServicos(pagina) {
        fetch('api/servicos?pagina=' + (pagina - 1) + "&status=3")
            .then(response => response.json())
            .then(servicos => {
                var tbody = document.getElementById("canceladosTabela"); 
                tbody.innerHTML = "";

                servicos.content.forEach(servico => {
                    var tr = document.createElement("tr");
                    tr.className = "align-middle";

                    tr.innerHTML = `
                    <td>${servico.id}</td>
                    <td style="width: 20em;">${servico.nome}</td>
                    <td style="width: 150px;">R$ ${servico.preco.toFixed(2)}</td>
                    <td>${formatDate(servico.pagamentoPrevisto)}</td>
                    <td>${formatDate(servico.pagamentoFinal)}</td>
                    <td>${servico.status}</td>
                    <td>
                        <div class="d-flex">
                        <a class="btn btn-info btn-sm mx-1" onclick="window.location.href = '/visualizar_servico?id=${servico.id}'; return false;">
                            <i class="fa-solid fa-eye"></i> Visualizar
                        </a>
                        <a class="btn btn-success btn-sm mx-1" onclick="if (confirm('Deseja realmente marcar o serviço como concluído?')) { atualizarStatusServico(${servico.id}, 'CONCLUIDO'); } return false;">
                            <i class="fa-solid fa-check"></i> Concluir
                        </a>
                        <a class="btn btn-primary btn-sm mx-1" onclick="if (confirm('Deseja realmente mover o serviço para em andamento?')) { atualizarStatusServico(${servico.id}, 'EM_ANDAMENTO'); } return false;">
                            <i class="fa-solid fa-rotate-left"></i> Em Andamento
                        </a>
                        </div>
                    </td>
                `;

                    tbody.appendChild(tr);
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
            alert(`Serviço ${novoStatus === 'CONCLUIDO' ? 'concluído' : (novoStatus === 'EM_ANDAMENTO' ? 'movido para em andamento' : 'cancelado')} com sucesso!`);
            location.reload();
        } else {
            alert(`Falha ao ${novoStatus === 'CONCLUIDO' ? 'concluir' : (novoStatus === 'EM_ANDAMENTO' ? 'mover para em andamento' : 'cancelar')} o serviço.`);
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
