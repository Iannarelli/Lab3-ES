package tp;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Jogo implements Serializable {

	private int rank;
	private String name;
	private String platform;
	private int year;
	private String genre;
	private String publisher;
	private int NASales;
	private int EUSales;
	private int JPSales;
	private int OtherSales;
	private int GlobalSales;

	public Jogo(int rank, String name, String platform, int year, String genre, String publisher, int NASales,
			int EUSales, int JPSales, int OtherSales, int GlobalSales) {
		setRank(rank);
		setName(name);
		setPlatform(platform);
		setYear(year);
		setGenre(genre);
		setPublisher(publisher);
		setNASales(NASales);
		setEUSales(EUSales);
		setJPSales(JPSales);
		setOtherSales(OtherSales);
		setGlobalSales(GlobalSales);
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getNASales() {
		return NASales;
	}

	public void setNASales(int nASales) {
		NASales = nASales;
	}

	public int getEUSales() {
		return EUSales;
	}

	public void setEUSales(int eUSales) {
		EUSales = eUSales;
	}

	public int getJPSales() {
		return JPSales;
	}

	public void setJPSales(int jPSales) {
		JPSales = jPSales;
	}

	public int getOtherSales() {
		return OtherSales;
	}

	public void setOtherSales(int otherSales) {
		OtherSales = otherSales;
	}

	public int getGlobalSales() {
		return GlobalSales;
	}

	public void setGlobalSales(int globalSales) {
		GlobalSales = globalSales;
	}

}