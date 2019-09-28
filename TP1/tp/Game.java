package tp;

import java.io.Serializable;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class Game implements Serializable {

	private int rank;
	private String name;
	private String platform;
	private int year;
	private String genre;
	private String publisher;
	private double NASales;
	private double EUSales;
	private double JPSales;
	private double OtherSales;
	private double GlobalSales;

	public Game() {
	}

	public Game(int rank, String name, String platform, int year, String genre, String publisher, double NASales,
			double EUSales, double JPSales, double OtherSales, double GlobalSales) {
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

	public double getNASales() {
		return NASales;
	}

	public void setNASales(double nASales) {
		NASales = nASales;
	}

	public double getEUSales() {
		return EUSales;
	}

	public void setEUSales(double eUSales) {
		EUSales = eUSales;
	}

	public double getJPSales() {
		return JPSales;
	}

	public void setJPSales(double jPSales) {
		JPSales = jPSales;
	}

	public double getOtherSales() {
		return OtherSales;
	}

	public void setOtherSales(double otherSales) {
		OtherSales = otherSales;
	}

	public double getGlobalSales() {
		return GlobalSales;
	}

	public void setGlobalSales(double globalSales) {
		GlobalSales = globalSales;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		StringBuilder string = new StringBuilder();
		string.append(name + "\n" + rank + "\t" + platform + "\t\t" + year + "\t" + genre);
		if(genre.length() < 8)
			string.append("\t\t");
		else
			string.append("\t");
		if (publisher.length() < 8)
			string.append(publisher + "\t\t\t\t\t");
		else if (publisher.length() < 16)
			string.append(publisher + "\t\t\t\t");
		else if (publisher.length() < 24)
			string.append(publisher + "\t\t\t");
		else if (publisher.length() < 32)
			string.append(publisher + "\t\t");
		else
			string.append(publisher + "\t");
		string.append(df.format(NASales) + "\t    ");
		string.append(df.format(EUSales) + "\t");
		string.append(df.format(JPSales) + "\t    ");
		string.append(df.format(OtherSales) + "\t");
		string.append(df.format(GlobalSales));
		return string.toString();
	}

}