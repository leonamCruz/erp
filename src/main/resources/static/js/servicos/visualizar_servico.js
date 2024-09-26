document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("editarServico").addEventListener("click", function() {
        const id = document.getElementById("id").value.trim(); 
        const servicoData = {
            id: id,
            nome: document.getElementById("nome").value.trim(),
            preco: parseFloat(document.getElementById("preco").value.replace("R$", "").trim()),
            status: document.getElementById("status").value.trim(),
            tipoPagamentoId: document.getElementById("tipoPagamentoId").value.trim(),
            pagamentoPrevisto: document.getElementById("pagamentoPrevisto").value.trim(),
            pagamentoFinal: document.getElementById("pagamentoFinal").value.trim(),
            descricao: document.getElementById("descricao").value.trim(),
            clienteId: document.getElementById("clienteId").value.trim() 
        };

        fetch(`/api/servicos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(servicoData)
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(errorText => {
                    throw new Error(errorText);
                });
            }
            return response.json();
        })
        .then(data => {
            alert("Serviço atualizado com sucesso!");
            window.history.back();
        })
        .catch(error => {
            alert("Erro: " + error.message);
        });
    });
});
