# AppCalculator - Calculadora de Salário (Android)

## Resumo
Aplicativo Android desenvolvido em Java que exige cadastro e login local do usuário para autenticação, oferecendo uma Calculadora de Salário. A calculadora aplica descontos de INSS e IR, calcula o salário-família quando aplicável e exibe o salário líquido detalhado.

---

## Objetivos principais
- Permitir cadastro de usuário com `id` (UUID), nome, email e senha.
- Autenticação local via login para acesso à calculadora.
- Tela de cálculo de salário com entrada de dados e exibição dos resultados.
- Implementar as regras de cálculo conforme especificação (INSS, IR, salário-família).
- Código organizado, testável e documentado.

---

## Requisitos técnicos mínimos
- Linguagem: Java (Android Studio)
- Arquitetura: MVP
- Min SDK: 21 (a definir)
- Build: Gradle

---

## Fluxo do usuário
1. Ao abrir o app, o usuário visualiza a tela de Login com opção para Cadastrar.
2. No cadastro, o usuário informa: `id` (UUID gerado), nome, email e senha. Após validação, os dados são armazenados localmente com segurança.
3. Após login bem-sucedido, o usuário acessa a tela da Calculadora de Salário.

---

## Entradas da calculadora
- Nome do funcionário (texto)
- Salário Bruto (numérico)
- Sexo (Masculino / Feminino — radio button)
- Número de Filhos (inteiro)

---

## Validações de entrada
- Nome: obrigatório, máximo 80 caracteres
- Senha: mínimo 6 caracteres
- Salário bruto: número positivo, limite prático 0 < salário ≤ 1.000.000
- Número de filhos: inteiro ≥ 0
- Sexo: obrigatório (Masculino ou Feminino)

---

## Regras de cálculo

### INSS (desconto sobre o salário bruto — flat rate por faixa)
| Faixa Salarial (R$)       | Alíquota (%) |
|---------------------------|--------------|
| Até 1.212,00              | 7,5          |
| De 1.212,01 até 2.427,35  | 9            |
| De 2.427,36 até 3.641,03  | 12           |
| De 3.641,04 até 7.087,22  | 14           |

**Cálculo:**  
`descontoINSS = salarioBruto * (alíquota / 100)`

---

### IR (desconto sobre o salário bruto — flat rate por faixa)
| Faixa Salarial (R$)       | Alíquota (%) |
|---------------------------|--------------|
| Até 1.903,98              | Isento (0%)  |
| De 1.903,99 até 2.826,65  | 7,5          |
| De 2.826,66 até 3.751,05  | 15           |
| De 3.751,06 até 4.664,68  | 22,5         |

**Cálculo:**  
`descontoIR = salarioBruto * (alíquota / 100)`

---

### Salário-Família
- Se `salarioBruto ≤ 1.212,00`:  
  `salarioFamilia = 56,47 * numeroDeFilhos`
- Caso contrário:  
  `salarioFamilia = 0`

---

### Salário Líquido
salarioLiquido = salarioBruto - descontoINSS - descontoIR + salarioFamilia


---

## Exibição dos resultados (UI/UX)
- Cabeçalho: “Sr.” ou “Sra.” seguido do nome, conforme sexo.
- Seção “Descontos” com:
  - Desconto INSS: R$ X,XX
  - Desconto IR: R$ Y,YY
  - Salário-Família: R$ Z,ZZ (exibido apenas se > 0)
- Destaque visual para o Salário Líquido: R$ W,WW (fonte maior)
- Exibir também o Salário Bruto para conferência.
- Botão “Novo Cálculo” para limpar o formulário.

---

## Critérios de aceite (QA)
- Cadastro armazena credenciais com segurança e permite login por email/senha.
- Tela de cálculo aceita entradas válidas e rejeita inválidas com mensagens claras.
- Para um conjunto dado de entradas, os cálculos exibidos devem estar corretos.
- App não mantém histórico (sem CRUD).
- Testes unitários contemplando lógica de INSS, IR, salário-família e validações.

---

## Casos de teste exemplares

| Entrada                                 | Resultados esperados                                   |
|----------------------------------------|-------------------------------------------------------|
| salárioBruto = 1.000,00<br>sexo = M<br>filhos = 2 | INSS: 1.000 * 7,5% = R$ 75,00<br>IR: isento = R$ 0,00<br>Salário-Família: 56,47 * 2 = R$ 112,94<br>Salário líquido: 1.000 - 75 - 0 + 112,94 = R$ 1.037,94 |
| salárioBruto = 3.000,00<br>sexo = F<br>filhos = 0 | INSS: 3.000 * 12% = R$ 360,00<br>IR: 3.000 * 15% = R$ 450,00<br>Salário-Família: R$ 0<br>Salário líquido: 3.000 - 360 - 450 = R$ 2.190,00 |

---

## Contato
Qualquer dúvida ou sugestão, abra uma issue ou entre em contato.

---
