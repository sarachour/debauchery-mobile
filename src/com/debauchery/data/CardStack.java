package com.debauchery.data;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class CardStack implements Parcelable {
	public static class Card implements Parcelable {
		String data;
		String type;
		Card(Parcel p){
			this.type = p.readString();
			this.data = p.readString();
		}
		Card(String type, String data){
			this.data = data;
			this.type = type;
		}
		public String getType(){
			return type;
		}
		public String getData(){	
			return data;
		}
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			dest.writeString(this.type);
			dest.writeString(this.data);
			
		}
		public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
	        public Card createFromParcel(Parcel in) {
	            return new Card(in);
	        }

	        public Card[] newArray(int size) {
	            return new Card[size];
	        }
	    };
	}
	public class TextCard extends Card {

		TextCard(String data) {
			super("text", data);
			// TODO Auto-generated constructor stub
		}
		
	}
	public class ImageCard extends Card {

		ImageCard(String data) {
			super("image", data);
			// TODO Auto-generated constructor stub
		}
		
	}
	List<Card> cards;
	int nplayers;
	int curr;
	public CardStack(Parcel p){
		cards = new ArrayList<Card>();
		this.nplayers = p.readInt();
		this.curr = p.readInt();
		p.readTypedList(cards, Card.CREATOR);
	}
	public CardStack(int nplayers){
		cards = new ArrayList<Card>();
		this.nplayers = nplayers;
		this.curr = 0;
	}
	public void addImageCard(String path){
		cards.add(new ImageCard(path));
		curr++;
	}
	public void addTextCard(String data){
		cards.add(new TextCard(data));
		curr++;
	}
	public boolean isEnd(){
		return (nplayers == curr);
	}
	public String getImageName(){
		return "buffer"+this.curr+".png";
	}
	public Card get(int i){
		return cards.get(i);
	}
	public int getNumPlayers(){
		return this.nplayers;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	public static final Parcelable.Creator<CardStack> CREATOR = new Parcelable.Creator<CardStack>() {
        public CardStack createFromParcel(Parcel in) {
            return new CardStack(in);
        }

        public CardStack[] newArray(int size) {
            return new CardStack[size];
        }
    };
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeInt(this.nplayers);
		arg0.writeInt(this.curr);
		arg0.writeTypedList(cards);
	}
	
	
}
