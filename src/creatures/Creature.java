package creatures;

import exceptions.NotEnoughIQ;
import item.Item;

import java.io.IOException;

public abstract class Creature implements MoveAction{
    public enum Gender {MALE, FEMAALE, UNKNOWN};
    public enum Act {
        FLY("полетело"),
        RUN("побежал"),
        JUMP("скакала"),
        RUSH("помчался");

        private final String action;
        Act(String action){
            this.action = action;
        };

        public String getAction() {return this.action;}
    }

    private Act MyAct;
    private Gender MyGender;
    private String Name;
    private double speed;
    private Item lastRoom;
    // Наличие запаха у существа
    private boolean smell;
    // true если сушество первое в погоне
    private boolean first;
    // true если сушество последнее в погоне
    private boolean last;
    private double distanceToTarget;


    public Creature(Gender gender, Act act, String name, Item room, boolean smell, boolean first, double distanceToTarget, double speed, boolean last)  {
        this.MyAct = act;
        this.MyGender = gender;
        this.Name = name;
        this.lastRoom = room;
        this.smell = smell;
        this.first = first;
        this.last = last;
        this.distanceToTarget = distanceToTarget;
        this.speed = speed;
    }

    public void changeDistance(Creature target) throws NotEnoughIQ {
        distanceToTarget = distanceToTarget + target.speed - speed;
    }

    @Override
    public String toString() {
        return getClass().getName() +  "[Имя = " + this.Name + ", скорость = " + this.speed + ", дистанция до цели = " + this.distanceToTarget + "]";
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;

        if (otherObject == null || this.getClass() != otherObject.getClass()) return false;

        Creature other = (Creature) otherObject;
        return this.getName().equals(other.getName()) &&
                this.distanceToTarget == other.distanceToTarget &&
                this.speed == other.speed &&
                this.first == other.first &&
                this.last == other.last &&
                this.smell == other.smell &&
                this.lastRoom.equals(other.lastRoom) &&
                this.MyGender == other.MyGender &&
                this.MyAct == other.MyAct;
    }

    public String getName() { return this.Name; }

    public Gender getMyGender() {
        return this.MyGender;
    }

    public double getSpeed() {
        return this.speed;
    }

    public Item getLastRoom() {
        return this.lastRoom;
    }

    public void setLastRoom(Item room) {this.lastRoom = room;}

    public boolean getSmell() {
        return this.smell;
    }

    public boolean getFirst() { return this.first; }

    public void setFirst(boolean first) {this.first = first;}

    public boolean getLast() { return this.last; }

    public void setLast(boolean last) {this.last = last;}

    public void setDistanceToTarget(double distance) {this.distanceToTarget = distance;}

    public double getDistanceToTarget() { return this.distanceToTarget; }

    public Act getMyAct() {return MyAct;}
}
