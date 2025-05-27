# Documentação do Evento Programável de Avaliação de Parceiros
![Logo da ArgoFruta](https://argofruta.com/wp-content/uploads/2021/05/Logo-text-white-1.png)
# Visão Geral
Este é um evento programável (PersistenceEvent) que é acionado antes da atualização de registros na tela de parceiros no sistema Sankhya. Ele monitora especificamente alterações nos campos de avaliação (AD_AVALIACAO) e observação (AD_OBSAVAL), disparando ações quando a avaliação é marcada como "RUIM".

# Funcionalidades Principais
1. Monitoramento de Alterações
Verifica se os campos AD_AVALIACAO ou AD_OBSAVAL foram modificados

Aciona as ações apenas quando a avaliação é definida como "RUIM"

# 2. Ações Realizadas
- a) Envio de E-mail
Gera um e-mail HTML estilizado contendo:

# Logo da empresa

- Emoji correspondente à avaliação (😠 para "RUIM")

- Dados do parceiro avaliado (nome/razão social)

- Motivo/observação da avaliação

- Informações do avaliador (usuário logado)

# b) Registro no Banco de Dados
- Armazena no histórico através do método repository.lancarHistoricoParceiro() com:

- Código do parceiro

- Tipo de avaliação ("RUIM")

# Observação

- Nome do usuário avaliador

- Data/hora da avaliação

- Nome do parceiro

# Fluxo de Execução
Coleta de Informações:

- Obtém dados do usuário logado (ID, nome, grupo, email)

- Captura os valores dos campos de avaliação e observação

- Identifica o parceiro sendo avaliado (código e nome)

- Verificação de Modificações:

- Checa se os campos relevantes foram alterados

- Valida se a avaliação é "RUIM"

# Processamento:

- Gera o conteúdo HTML do e-mail com estilização responsiva

- Utiliza emojis e cores condicionais baseados na avaliação

- Dispara o e-mail para os usuários relevantes

- Registra o histórico no banco de dados

# Métodos Auxiliares
### getEmojiHtml(String avaliacao)
- Retorna emoji HTML correspondente à avaliação:

- BOM: 😊

- REGULAR: 😐

- RUIM: 😠

- Outros: ❓

### getRatingColor(String avaliacao)
Retorna cor CSS correspondente à avaliação:

- BOM: Verde (#4CAF50)

- REGULAR: Amarelo (#FFC107)

- RUIM: Vermelho (#F44336)

- Outros: Cinza (#999)

# Dados Armazenados para Dashboard
- O método lancarHistoricoParceiro persiste os dados que permitirão:

- Consultas históricas por período

- Análise por parceiro

- Relatórios por avaliador

- Tendências de avaliações ao longo do tempo

# Observações
- O e-mail utiliza template HTML responsivo com estilização moderna

- O sistema identifica explicitamente quando os campos de interesse são modificados

- Todas as ações são condicionadas à avaliação "RUIM" para evitar notificações desnecessárias

  ### USUARIOS 
- USUARIO RESPONSAVEL: Valdemi Andrade
- EMAIL : valdemi.filho@argofruta.com
