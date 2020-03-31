package com.victor.element;

public interface Elements {

	String inputNome = "//*[@id=\"nome\"]";
	String inputCpf = "//*[@id=\"cpf\"]";
	String inputRg = "//*[@id=\"rg\"]";
	String inputEmail = "//*[@id=\"email\"]";
	String inputConfirmaEmail = "//*[@id=\"emailVerifique\"]";
	String inputDdd = "//*[@id=\"ddd\"]";
	String inputTelefone = "//*[@id=\"telefone\"]";
	String inputCep = "//*[@id=\"cep\"]";
	String inputEnderecoNumero = "//*[@id=\"numero\"]";
	String inputEnderecoComplemento = "//*[@id=\"complemento\"]";
	String inputEnderecoBairro = "//*[@id=\"bairro\"]";
	String inputEnderecoBairroHidden = "//*[@id=\"hfCodigoBairro\"]";
	String inputComplemento = "//*[@id=\"textoComplemento\"]";

	String inputRadioLocalBarulhoViaPublica = "//*[@id=\"Pergunta_1\"]/div[2]/label/child::*";
	String inputRadioMotivoAglomeracaoDePessoas = "//*[@id=\"Pergunta_2\"]/div[7]/label/child::*";
    String inputRadioMotivoAparelhoDeSom = "//*[@id=\"Pergunta_2\"]/div[3]/label/child::*";
	String inputRadioQtdPessoasAte50 = "//*[@id=\"Pergunta_4\"]/div[2]/label/child::*";



	String inputRadioLocalBarulhoResidencia = "//*[@id=\"Pergunta_1\"]/div[3]/label/child::*";
	String inputRadioMotivoOutro = "//*[@id=\"Pergunta_7\"]/div[6]/label/child::*";
	String inputRadioMotivoMusica = "//*[@id=\"Pergunta_7\"]/div[3]/label/child::*";
	String inputRadioNao = "//*[@id=\"radioContatoNao\"]";

	String checkDeclaro = "//*[@id=\"checkDeclaracao\"]";

	String btnProximoCadastro = "//*[@id=\"botaoCadastroProximo\"]";
	String btnProximoEndereco = "//*[@id=\"botaoEnderecoProximo\"]";
	String btnProximoOrigem = "//*[@id=\"botaoOrigemProximo\"]";
	String btnEnviar = "//*[@id=\"botaoEnviar\"]";

	String imgLupa = "/html/body/div[1]/form/div[2]/div/div[3]/div[8]/div[1]/img";

	String complemento = "Unico individuo ouvindo som em seu veiculo na rua, VW GOL MI/1998, placa ctz 3620, cor vermellha"
			+ " Não é possível fazer contato com a viatura devido represálias dos moradores";

	String captcha = "//*[@id=\"CaptchaImage\"]";
	String captchaInput = "//*[@id=\"CaptchaInputText\"]";
	String captchaRefresh = "/html/body//a[@href=\'#CaptchaImage\']";

	String boxError = "//*[@id=\"mensagemErroInformacoes\"]";
	String boxErrorMessage = "//*[@id=\"textoErroInformacoes\"]/b";

	String popUpSucess = "//*[@id=\"dialogMensagemPopUp\"]";

}