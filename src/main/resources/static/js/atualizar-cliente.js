document.addEventListener('DOMContentLoaded', async function () {
    document.getElementById('clientForm').addEventListener('submit', async function (event) {
        event.preventDefault()
        const url = new URL(window.location.href);
        const params = new URLSearchParams(url.search);
        const param = params.get('id');
        const id = Number(param);
        
        const nome = document.querySelector('#nome').value.trim()
        const cpfOrCnpj = document.querySelector('#cpf-or-cnpj').value.trim()
        const numeroContato = document.querySelector('#numero').value.trim()
        const cep = document.querySelector('#cep').value.trim()
        const endereco = document.querySelector('#rua').value.trim()
        const bairro = document.querySelector('#bairro').value.trim()
        const cidade = document.querySelector('#cidade').value.trim()
        const uf = document.getElementById('estado').value
        const numeroCasa = parseInt(document.querySelector('#numero-endereco').value.trim(), 10) || 0

        const identificacao = cpfOrCnpj.replace(/\D/g, '')

        const formData = { nome, identificacao, numeroContato, cep, endereco, bairro, cidade, uf, numeroCasa };

        const api = `/api/cliente/${id}`
        const metodo = 'PUT'

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
