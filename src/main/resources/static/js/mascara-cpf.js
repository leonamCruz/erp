document.addEventListener('DOMContentLoaded', function() {
    const cpfInput = document.getElementById('cpf-or-cnpj')
    const cpfRadio = document.getElementById('cpf')
    const cnpjRadio = document.getElementById('cnpj')

    cpfInput.addEventListener('input', function(event) {
        let input = event.target.value
        input = input.replace(/\D/g, '')

        switch (true) {
            case cpfRadio.checked:
                if (input.length > 11) input = input.substring(0, 11)
                if (input.length > 6) input = input.replace(/^(\d{3})(\d{3})(\d{3})(\d{1,2})$/, '$1.$2.$3-$4')
                else if (input.length > 3) input = input.replace(/^(\d{3})(\d{3})$/, '$1.$2')
                break
            case cnpjRadio.checked:
                if (input.length > 14) input = input.substring(0, 14)
                if (input.length > 12) input = input.replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/, '$1.$2.$3/$4-$5')
                else if (input.length > 6) input = input.replace(/^(\d{2})(\d{3})(\d{3})$/, '$1.$2.$3')
                else if (input.length > 2) input = input.replace(/^(\d{2})(\d{3})$/, '$1.$2')
                break
            default:
                input = ''
        }

        event.target.value = input
    })
})
