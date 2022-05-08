package Model.gameboard;

public class Land extends Tile {
	private String description;
	private int price;
	private int buildHouseCost;
	private int buildHotelCost;
	private int[] rentCost = new int[6];
	public Land(String description, int price, int buildHouseCost, int buildHotelCost, int[] rentCost)
	{
		this.description = description;
		this.price = price;
		this.buildHouseCost = buildHouseCost;
		this.buildHotelCost = buildHotelCost;
		this.rentCost = rentCost;
		
	}
	void print(){
		System.out.println("I'm a land");
		System.out.println("Description: " + description);
		System.out.println("price: " + price);
		System.out.println("Build house cost: " + buildHouseCost);
		System.out.println("Build hotel cost: " + buildHotelCost);
		System.out.println("Rent cost 0 houses: " + rentCost[0]);
		System.out.println("Rent cost 1 houses: " + rentCost[1]);
		System.out.println("Rent cost 2 houses: " + rentCost[2]);
		System.out.println("Rent cost 3 houses: " + rentCost[3]);
		System.out.println("Rent cost 4 houses: " + rentCost[4]);
		System.out.println("Rent cost 5 houses: " + rentCost[5]);
	}
}
