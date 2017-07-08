package game.player.inventory;

import game.player.inventory.items.Item;

import java.awt.image.BufferedImage;

public class Stack {

	private Item item;
	private int num;

	public Stack(Item item, int num) {
		this.item = item;
		this.num = num;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public boolean remove(int items) {
		if (num < items) return false;
		num -= items;
		return true;
	}
	
	public void add(int items) {
		num += items;
	}
	
	public boolean equals(Object other) {
		if (getClass() != other.getClass())
			return false;
		if (item.getId() != ((Stack)other).getItem().getId())
			return false;
		return true;
	}

	public BufferedImage getImg() {
		return item.getImage();
	}
	
	public String getName() {
		return item.getName();
	}	

	public String toString() {
		return new Integer(num).toString();
	}

	public void action(int x, int y) {
		item.action(x, y);
	}
}
