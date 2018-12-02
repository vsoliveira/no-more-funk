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

	String inputRadioLocalBarulho = "//*[@id=\"Pergunta_1\"]/div[3]/label/child::*";
	String inputRadioMotivo = "//*[@id=\"Pergunta_7\"]/div[3]/label/child::*";
	String inputRadioNao = "//*[@id=\"radioContatoNao\"]";

	String checkDeclaro = "//*[@id=\"checkDeclaracao\"]";

	String btnProximoCadastro = "//*[@id=\"botaoCadastroProximo\"]";
	String btnProximoEndereco = "//*[@id=\"botaoEnderecoProximo\"]";
	String btnProximoOrigem = "//*[@id=\"botaoOrigemProximo\"]";
	String btnEnviar = "//*[@id=\"botaoEnviar\"]";

	String imgLupa = "/html/body/div[1]/form/div[2]/div/div[3]/div[8]/div[1]/img";

	String complemento = "Grupo de pessoas ouvindo som automotivo de volume elevado em via publica. "
			+ "Som provêm de um GM/ASTRA HATCH 5P - 2002 - 2003 - Prata, PLACA AKR-8657"
			+ "Não é possível fazer contato com a viatura devido represálias dos moradores da comunidade";

	String captcha = "//*[@id=\"CaptchaImage\"]";
	String captchaInput = "//*[@id=\"CaptchaInputText\"]";
	String captchaRefresh = "/html/body//a[@href=\'#CaptchaImage\']";

}