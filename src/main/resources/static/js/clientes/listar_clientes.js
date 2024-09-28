document.addEventListener('DOMContentLoaded', async function () {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    const pagina = params.get('pagina');

    function loadClientes(pagina) {
        fetch('api/cliente/page?pagina=' + (pagina - 1))
            .then(response => response.json())
            .then(clientes => {

                var accordionExample = document.getElementById("accordionExample");
                accordionExample.innerHTML = "";

                clientes.content.forEach((cliente, index) => {
                    var accordionItem = document.createElement("div");
                    accordionItem.className = "accordion-item  ";

                    accordionItem.innerHTML = `
                <h2 class="accordion-header" id="heading${index}">
                    <button class="accordion-button collapsed d-flex align-items-center" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${index}" aria-expanded="false" aria-controls="collapse${index}">
                        <i class="fa-solid fa-user me-2"></i> 
                        <span class="fw-bold">${cliente.nome}</span>
                    </button>
                </h2>
                <div id="collapse${index}" class="accordion-collapse collapse" aria-labelledby="heading${index}" data-bs-parent="#accordionExample">
                    <div class="accordion-body bg-light">
                        <div class="row">
                            <div class="col-md-6">
                                <ul class="list-group">
                                    <li class="list-group-item mb-2"><strong>Id:</strong> ${cliente.id}</li>
                                    <li class="list-group-item mb-2"><strong>Nome:</strong> ${cliente.nome}</li>
                                    <li class="list-group-item mb-2"><strong>Contato:</strong> ${formatPhoneNumber(cliente.numeroContato)}</li>
                                </ul>
                            </div>
                            <div class="col-md-6">
                                <ul class="list-group">
                                    <li class="list-group-item mb-2"><strong>Endereço:</strong> ${cliente.endereco}</li>
                                    <li class="list-group-item mb-2"><strong>Bairro:</strong> ${cliente.bairro}</li>
                                    <li class="list-group-item mb-2"><strong>Cidade:</strong> ${cliente.cidade} - ${cliente.uf}</li>
                                </ul>
                            </div>
                        </div>
                        <div class="d-flex justify-content-end mt-3">
                            <a class="btn btn-outline-primary btn-sm mx-1" onclick="if (confirm('Deseja realmente atualizar o cliente?')) { window.location.href = '/atualizar_cliente?id=${cliente.id}'; } return false;">
                                <i class="fa-solid fa-pen-to-square"></i> Editar
                            </a>
                            <a href="#" class="btn btn-outline-danger btn-sm mx-1" onclick="deleteCliente(${cliente.id});">
                                <i class="fa-solid fa-trash-can"></i> Excluir
                            </a>
                        </div>
                    </div>
                </div>
                `;

                    accordionExample.appendChild(accordionItem);
                });
            })
            .catch(error => {
                alert('Erro na requisição:', error);
            });
    }

    loadClientes(pagina);

});

function deleteCliente(id) {
    if (confirm('Deseja realmente deletar o cliente?')) {
        fetch(`api/cliente/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('Falha ao deletar o cliente.');
            }
        }).catch(error => {
            console.error('Erro:', error);
        });
    }
}
function formatPhoneNumber(value) {
    const cleaned = value.replace(/\D/g, '')

    if (cleaned.length === 10) {
        return cleaned
            .replace(/^(\d{2})(\d{4})(\d{4})$/, '$1 $2-$3')
            .slice(0, 13)
    } else if (cleaned.length === 11) {
        return cleaned
            .replace(/^(\d{2})(\d{5})(\d{4})$/, '$1 $2-$3')
            .slice(0, 14)
    }

    return value
}