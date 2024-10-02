document.addEventListener("DOMContentLoaded", function() {
    new DatePicker('.data-input');
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


class DatePicker {
    constructor(selector) {
        this.dateInputs = document.querySelectorAll(selector);
        this.setMinDate(); 
    }

    setMinDate() {
        const hoje = new Date();
        const dia = String(hoje.getDate()).padStart(2, '0');
        const mes = String(hoje.getMonth() + 1).padStart(2, '0'); 
        const ano = hoje.getFullYear();
        const dataMinima = `${ano}-${mes}-${dia}`; 

        this.dateInputs.forEach(input => {
            input.setAttribute('min', dataMinima);
        });
    }
}