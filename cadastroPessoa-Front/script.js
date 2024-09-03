document.getElementById('formPessoa').addEventListener('submit', async (event) => {
    event.preventDefault();

    const nomePessoa = document.getElementById('nomePessoa').value;
    const cpfPessoa = document.getElementById('cpfPessoa').value;
    const numeroPessoa = document.getElementById('numeroPessoa').value;


    const addressData = {
        nomePessoa: nomePessoa,
        cpfPessoa: cpfPessoa,
        numeroPessoa: numeroPessoa,
    };

    try {
        const response = await fetch('http://localhost:8080/pessoa', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(addressData)
        });

        const result = await response.json();

        if (response.ok) {
            document.getElementById('message').innerText = 'Cadastrado com sucesso!';
            document.getElementById('formPessoa').reset();
        } else {
            document.getElementById('message').innerText = `Erro: ${result.message}`;
        }
    } catch (error) {
        document.getElementById('message').innerText = 'Erro na comunicação com o servidor.';
    }
});
