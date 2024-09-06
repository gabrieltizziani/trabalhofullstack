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

const modal = document.getElementById('modal');
const openModalBtn = document.getElementById('openModalBtn');
const closeModalBtn = document.getElementById('closeModalBtn');

openModalBtn.addEventListener('click', async () => {
    modal.style.display = 'block';

    try {
        const response = await fetch('http://localhost:8080/pessoa');
        if (response.ok) {
            const pessoas = await response.json();
            const tabelaBody = document.querySelector('#tabelaUsuarios tbody');
            tabelaBody.innerHTML = ''; // Limpa o conteúdo anterior

            if (pessoas.length > 0) {
                pessoas.forEach(pessoa => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${pessoa.nomePessoa}</td>
                        <td>${pessoa.cpfPessoa}</td>
                        <td>${pessoa.numeroPessoa}</td>
                        <td>
                            <i class="fas fa-edit edit-btn" data-id="${pessoa.idPessoa}" style="cursor:pointer; color: #4CAF50;"></i>
                            <i class="fas fa-trash delete-btn" data-id="${pessoa.idPessoa}" style="cursor:pointer; color: #f44336; margin-left: 10px;"></i>
                        </td>
                    `;
                    tabelaBody.appendChild(row);
                });

                    
                    document.querySelectorAll('.edit-btn').forEach(button => {
                        button.addEventListener('click', (event) => {
                            const idPessoa = event.target.getAttribute('data-id');
                            
                            console.log(`Editar pessoa com ID: ${idPessoa}`);
                        });
                    });

                    document.querySelectorAll('.delete-btn').forEach(button => {
                        button.addEventListener('click', async (event) => {
                            const idPessoa = event.target.getAttribute('data-id');
                            
                            if (confirm('Tem certeza que deseja excluir esta pessoa?')) {
                                try {
                                    const response = await fetch(`http://localhost:8080/pessoa/${idPessoa}`, {
                                        method: 'DELETE'
                                    });
                    
                                    if (response.ok) {
                                        alert('Pessoa excluída com sucesso!');
                                        // Atualize a lista de pessoas após exclusão
                                        event.target.closest('tr').remove();
                                    } else {
                                        alert('Erro ao excluir pessoa.');
                                    }
                                } catch (error) {
                                    alert('Erro na comunicação com o servidor.');
                                }
                            }
                        });
                    });
            } else {
                tabelaBody.innerHTML = '<tr><td colspan="3">Nenhuma pessoa cadastrada.</td></tr>';
            }
        } else {
            document.getElementById('usuariosCadastrados').innerText = 'Erro ao carregar os usuários cadastrados.';
        }
    } catch (error) {
        document.getElementById('usuariosCadastrados').innerText = 'Erro na comunicação com o servidor.';
    }
});





closeModalBtn.addEventListener('click', () => {
    modal.style.display = 'none';
});
