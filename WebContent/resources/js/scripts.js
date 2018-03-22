function verificar(xhr, status, args, dlg, tb) {
        if(args.validationFailed) {
            PF(dlg).jq.effect("shake", {times:5}, 100);
        }
        else {
            PF(dlg).hide();
            PF(tb).clearFilters();
        }
    }

function logar(login, senha){
	var login = loginForm.login.value;
	var senha = loginForm.senha.value;
	
	if(login == ""){
		alert('Preencha o campo login.');
		loginForm.login.focus();
		return false;
	}
	if(senha == "" || senha.length <=5){
		alert('Preencha o campo senha.');
		loginForm.senha.focus();
		return false;
	}
}

function retornar() {
    history.go(-1);
}