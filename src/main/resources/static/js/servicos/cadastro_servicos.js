document.addEventListener('DOMContentLoaded', async function () {
    new DatePicker('.data-input');

    document.getElementById('servicoForm').addEventListener('submit', async function (event) {
        event.preventDefault();
        const nome = document.querySelector('#nome').value.trim();
        const preco = document.querySelector('#preco').value.trim();
        const clienteId = document.querySelector('#clienteId').value.trim();
        const tipoPagamentoId = document.querySelector('#tipoPagamentoId').value.trim();
        const pagamentoPrevisto = document.querySelector('#pagamentoPrevisto').value.trim();
        const pagamentoFinal = document.querySelector('#pagamentoFinal').value.trim();
        const descricao = document.querySelector('#descricao').value.trim();

        const formData = { nome, preco, clienteId, tipoPagamentoId, pagamentoPrevisto, pagamentoFinal, descricao };
        const api = '/api/servicos';
        const metodo = 'POST';

        try {
            const response = await fetch(api, {
                method: metodo,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            })

            if (response.ok || response.status === 201) {
                const data = await response.text()
                console.log('Sucesso:', data)
                alert('Formulário enviado com sucesso!')
            } else {
                let errorMessage = 'Erro desconhecido'
                try {
                    const errorData = await response.json()
                    console.log(errorData)

                    if (errorData && typeof errorData === 'object') {
                        const errorMessages = Object.values(errorData)
                        if (errorMessages.length > 0) {
                            errorMessage = errorMessages[0]
                        }
                    }

                } catch (jsonError) {
                    errorMessage = await response.text()
                }

                alert(`Erro ao enviar o formulário: ${errorMessage}`)
            }
        } catch (error) {
            console.error('Erro na requisição:', error)
            alert('Ocorreu um erro ao enviar o formulário. Por favor, tente novamente.')
        }
    })
})

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