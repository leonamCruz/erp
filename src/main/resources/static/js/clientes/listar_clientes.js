document.addEventListener('DOMContentLoaded', async function () {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    const pagina = params.get('pagina');

    function loadClientes(pagina) {
        fetch('api/cliente/page?pagina=' + (pagina - 1))
            .then(response => {
                return response.json();
            })
            .then(clientes => {

                var tbody = document.getElementById("clienteTableBody");
                tbody.innerHTML = "";

                clientes.content.forEach(cliente => {
                    var tr = document.createElement("tr");
                    tr.className = "align-middle";

                    tr.innerHTML = `
                    <td>${cliente.id}</td>
                    <td style="width: 20em;">${cliente.nome}</td>
                    <td style="width: 150px;">${formatPhoneNumber(cliente.numeroContato)}</td>
                    <td>
                        <span>${cliente.endereco}</span>,
                        <span>${cliente.bairro}</span>,
                        <span>${cliente.cidade}</span> -
                        <span>${cliente.uf}</span>
                    </td>
                    <td style="width: 10px;">
                        <div class="d-flex">
                            <a class="btn btn-primary btn-sm mx-1" onclick="if (confirm('Deseja realmente atualizar o cliente?')) { window.location.href = '/atualizar_cliente?id=${cliente.id}'; } return false;"><i class="fa-solid fa-pen-to-square"></i></a>
                            <a href="#" class="btn btn-danger btn-sm mx-1" onclick="deleteCliente(${cliente.id});"><i class="fa-solid fa-trash-can"></i></a>           
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