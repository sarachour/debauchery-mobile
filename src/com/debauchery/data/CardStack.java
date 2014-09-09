package com.debauchery.data;

import java.util.ArrayList;
import java.util.List;

import com.debauchery.sketch.Action;
import com.debauchery.sketch.SketchPadData;

import android.os.Parcel;
import android.os.Parcelable;

public class CardStack implements Parcelable {
	List<Card> cards;
	int nplayers;
	int curr;
	private int startMode;
	public CardStack(Parcel p){
		cards = new ArrayList<Card>();
		this.nplayers = p.readInt();
		this.curr = p.readInt();
		this.startMode = DESCRIBING;
		p.readTypedList(cards, Card.CREATOR);
	}
	public CardStack(int nplayers){
		cards = new ArrayList<Card>();
		this.nplayers = nplayers;
		this.curr = 0;
	}
	public void addImageCard(SketchPadData sketchPadData){
		cards.add(new ImageCard(sketchPadData));
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
	public static final int DRAWING = 0;
	public static final int DESCRIBING = 	1;
	public void setStartMode(int mode){
		this.startMode = mode;
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeInt(this.nplayers);
		arg0.writeInt(this.curr);
		arg0.writeTypedList(cards);
	}
	
	
}
