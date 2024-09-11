document.addEventListener('DOMContentLoaded', async function () {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    const pagina = params.get('pagina');

    function loadClientes(pagina) {
        fetch('api/cliente/page?pagina=' + (pagina-1))
            .then(response => {
                return response.json();
            })
            .then(clientes => {
                
                var tbody = document.getElementById("clienteTableBody");
                tbody.innerHTML = ""; // Limpa a tabela antes de adicionar novos dados

                clientes.content.forEach(cliente => {
                    var tr = document.createElement("tr");
                    tr.className = "align-middle";

                    tr.innerHTML = `
                    <td>${cliente.id}</td>
                    <td style="width: 20em;">${cliente.nome}</td>
                    <td>${cliente.numeroContato}</td>
                    <td>
                        <span>${cliente.endereco}</span>,
                        <span>${cliente.bairro}</span>,
                        <span>${cliente.cidade}</span> -
                        <span>${cliente.uf}</span>
                    </td>
                    <td>
                        <div class="d-flex">
                            <a class="btn btn-primary btn-sm mx-1"><i class="fa-solid fa-pen-to-square"></i></a>
                            <a class="btn btn-danger btn-sm mx-1" onclick="return confirm('Deseja realmente deletar o cliente?');"><i class="fa-solid fa-trash-can"></i></a>
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