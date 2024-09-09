document.addEventListener('DOMContentLoaded', async function () {

    document.getElementById('clientForm').addEventListener('submit', async function (event) {
        event.preventDefault();

        // Coleta os valores dos campos do formulário
        const isCPF = document.querySelector('#cpf').checked;
        const nome = document.querySelector('#nome').value.trim();
        const cpfOrCnpj = document.querySelector('#cpf-or-cnpj').value.trim();
        const numeroContato = document.querySelector('#numero').value.trim();
        const cep = document.querySelector('#cep').value.trim();
        const endereco = document.querySelector('#rua').value.trim();
        const bairro = document.querySelector('#bairro').value.trim();
        const cidade = document.querySelector('#cidade').value.trim();
        const uf = document.getElementById('estado').value;
        const numeroCasa = parseInt(document.querySelector('#numero-endereco').value.trim(), 10) || 0;

        const apenasNumeros = cpfOrCnpj.replace(/\D/g, '');

        const cpf = apenasNumeros.length === 11 ? apenasNumeros : null;
        const cnpj = apenasNumeros.length === 14 ? apenasNumeros : null;

        // Dados que serão enviados
        let formData = {
            isCPF,
            nome,
            cpf,
            cnpj,
            numeroContato,
            cep,
            endereco,
            bairro,
            cidade,
            uf,
            numeroCasa
        };

        console.log("Dados do formulário:", formData); // Exibe os dados no console para verificação
        try {
            const response = await fetch('/api/cliente', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });
        
            if (response.ok || response.status === 201) {
                const data = await response.text();
                console.log('Sucesso:', data);
                alert('Formulário enviado com sucesso!');
            } else {
                let errorMessage = await response.text();
                const statusCode = response.status;
                alert(`Erro ao enviar o formulário (Código ${statusCode}): ${errorMessage}`);
                console.error(`Erro ${statusCode}: ${errorMessage}`);
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            alert('Ocorreu um erro ao enviar o formulário. Por favor, tente novamente.');
        }
    });
})
