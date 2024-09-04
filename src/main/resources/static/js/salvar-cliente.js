document.addEventListener('DOMContentLoaded', function () {
    let selectedUf = ''

    function handleDropdownClick(event) {
        if (event.target && event.target.matches('.dropdown-item')) {
            selectedUf = event.target.getAttribute('data-value')
        }
    }

    const menuLegal = document.querySelector('#menu-legal')
    menuLegal.addEventListener('click', handleDropdownClick)

    function submitForm() {
        const isCPF = document.querySelector('#cpf').checked
        const nome = document.querySelector('#nome').value.trim()
        const cpfOrCnpj = document.querySelector('#cpf-or-cnpj').value.trim()
        const numeroContato = document.querySelector('#numero').value.trim()
        const cep = document.querySelector('#cep').value.trim()
        const endereco = document.querySelector('#rua').value.trim()
        const bairro = document.querySelector('#bairro').value.trim()
        const cidade = document.querySelector('#cidade').value.trim()
        const uf = selectedUf
        const numeroCasa = parseInt(document.querySelector('#numero-endereco').value.trim(), 10) || 0

        const formData = {
            isCPF,
            nome,
            cpfOrCnpj,
            numeroContato,
            cep,
            endereco,
            bairro,
            cidade,
            uf,
            numeroCasa
        }

        fetch('/api/cliente', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Sucesso:', data)
            })
            .catch(error => {
                console.error('Erro:', error)
                alert('Ocorreu um erro ao enviar o formulário. Por favor, tente novamente.')
            })
    }

    const button = document.querySelector('#save-client-button')
    button.addEventListener('click', submitForm)
})
