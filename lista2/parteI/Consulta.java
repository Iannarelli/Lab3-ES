package parteI;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class Consulta implements Serializable {
	private int numConsulta;
	private int diaSemana;
	private int mes;
	private int ano;
	private LocalDateTime hora;
	private int cod_medico;
	private int cod_paciente;

	public Consulta(int numConsulta, int dia, int mes, int ano, LocalDateTime hora, int cod_medico, int cod_paciente) {
		super();
		this.numConsulta = numConsulta;
		this.diaSemana = dia;
		this.mes = mes;
		this.ano = ano;
		this.hora = hora;
		this.cod_medico = cod_medico;
		this.cod_paciente = cod_paciente;
	}

	public int getNumConsulta() {
		return numConsulta;
	}

	public void setNumConsulta(int numConsulta) {
		this.numConsulta = numConsulta;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int dia) {
		this.diaSemana = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public int getCod_medico() {
		return cod_medico;
	}

	public void setCod_medico(int cod_medico) {
		this.cod_medico = cod_medico;
	}

	public int getCod_paciente() {
		return cod_paciente;
	}

	public void setCod_paciente(int cod_paciente) {
		this.cod_paciente = cod_paciente;
	}
}