document.addEventListener('DOMContentLoaded',async function () {
    
    document.getElementById('clientForm').addEventListener('submit', async function(event) {
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
    
        // Dados que serão enviados
        let formData = {
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
        };
        
        console.log("Dados do formulário:", formData); // Exibe os dados no console para verificação
    
        try {
            // Faz a requisição assíncrona para enviar os dados
            const response = await fetch('/api/cliente', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });
    
            // Verifica se a resposta do servidor foi bem-sucedida
            if (response.ok) {
                const data = await response.json();
                console.log('Sucesso:', data);
                alert('Formulário enviado com sucesso!');
            } else {
                console.error('Erro na resposta do servidor:', response.statusText);
                alert('Erro ao enviar o formulário. Por favor, tente novamente.');
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            alert('Ocorreu um erro ao enviar o formulário. Por favor, tente novamente.');
        }
    });
    
})
