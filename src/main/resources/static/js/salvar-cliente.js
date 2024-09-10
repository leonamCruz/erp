document.addEventListener('DOMContentLoaded', async function () {

    document.getElementById('clientForm').addEventListener('submit', async function (event) {
        event.preventDefault()

        const isCPF = document.querySelector('#cpf').checked
        const nome = document.querySelector('#nome').value.trim()
        const cpfOrCnpj = document.querySelector('#cpf-or-cnpj').value.trim()
        const numeroContato = document.querySelector('#numero').value.trim()
        const cep = document.querySelector('#cep').value.trim()
        const endereco = document.querySelector('#rua').value.trim()
        const bairro = document.querySelector('#bairro').value.trim()
        const cidade = document.querySelector('#cidade').value.trim()
        const uf = document.getElementById('estado').value;
        const numeroCasa = parseInt(document.querySelector('#numero-endereco').value.trim(), 10) || 0;

        const apenasNumeros = cpfOrCnpj.replace(/\D/g, '')

        const cpf = apenasNumeros.length === 11 ? apenasNumeros : null
        const cnpj = apenasNumeros.length === 14 ? apenasNumeros : null

        let formDataCPF = {
            nome,
            cpf,
            numeroContato,
            cep,
            endereco,
            bairro,
            cidade,
            uf,
            numeroCasa
        }
        let formDataCNPJ = {
            nome,
            cnpj,
            numeroContato,
            cep,
            endereco,
            bairro,
            cidade,
            uf,
            numeroCasa
        }

        const api = 'http://localhost:8080/api/cliente/';
        let response;
        const metodo = 'POST'
        try {

            if(isCPF) {
                console.log("Dados do formulário:", formDataCPF)

                response = await fetch(api + 'cpf', {
                    method: metodo,
                    headers: {
                        'Content-Type':'application/json'
                    },
                    body: JSON.stringify(formDataCPF)
                })
            } else {
                console.log("Dados do formulário:", formDataCNPJ)

                response = await fetch(api + 'cnpj', {
                    method: metodo,
                    headers: {
                        'Content-Type':'application/json'
                    },
                    body: JSON.stringify(formDataCNPJ)
                })
            }
            if (response.ok || response.status === 201) {
                const data = await response.text();
                console.log('Sucesso:', data);
                alert('Formulário enviado com sucesso!');
            } else {
                let errorMessage = await response.text()
                const statusCode = response.status
                alert(`Erro ao enviar o formulário (Código ${statusCode}): ${errorMessage}`)
                console.error(`Erro ${statusCode}: ${errorMessage}`)
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            alert('Ocorreu um erro ao enviar o formulário. Por favor, tente novamente.')
        }
    })
})
