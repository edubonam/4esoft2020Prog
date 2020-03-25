package observer.cofre;

import java.util.ArrayList;
import java.util.List;

public class Cofre {

	private Boolean aberto;
	private Long senha;
	private List<CofreListenerAberto> listenersCofreAberto = new ArrayList<>();
	private List<CofreListenerFechado> listenersCofreFechado = new ArrayList<>();
	private List<CofreListenerSenha> listenersSenhaIncorreta = new ArrayList<>();
	
	public Cofre(Long senha) {
		this.senha = senha;
		this.aberto = Boolean.TRUE;
	}
	
	public void abrir(Long senha) {
		
		if(this.senha.equals(senha)) {
			this.aberto = Boolean.TRUE;
			this.listenersCofreAberto.forEach(l -> l.cofreFoiAberto());
		} else {
			
			this.listenersSenhaIncorreta.forEach(l -> l.aberturaSenhaIncorreta());
		}
	}
	
	public void fechar() {
		this.listenersCofreFechado.forEach(l -> l.cofreFoiFechado());
		this.aberto = Boolean.FALSE;
	}
	
	public Boolean isAberto() {
		
		return this.aberto;
	}
	
	public void addListenerAberto(CofreListenerAberto listener) {
		this.listenersCofreAberto.add(listener);
	}
	
	public void addListenerFechado(CofreListenerFechado listener) {
		this.listenersCofreFechado.add(listener);
	}
	
	public void addListenerSenha(CofreListenerSenha listener) {
		this.listenersSenhaIncorreta.add(listener);
	}
}
