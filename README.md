# Open ERP

##Descrição
Este projeto visa um ERP no estilo 'Hospede você mesmo', mantenha os dados da sua empresa nas suas mãos.

#Passo a Passo
É necessário possuir um banco de dados MariaDB e a um E-mail que permita acesso de app's terceiros.

Insira as váriaveis de ambiente na máquina:
DB_PASSWORD=coloquesuasenhadobd
DB_DEFAULT=nomedobd
DB_USER=nomedousuariodobd
DB_URL=jdbc:mariadb://coloque-aqui-o-enderenco-do-bd:3306
MAIL=seu@endereco.email
MAIL_PASS=senhadoseuemail

Clone o Repositório https://github.com/leonamCruz/erp

docker-compose up -d

Seja feliz.
