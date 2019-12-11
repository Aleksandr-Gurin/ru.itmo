import creatures.*;
import place.*;

import java.util.ArrayList;

public class Game {
    ArrayList<Creature> characters = new ArrayList<>();
    Communication apartments;

    public Game(Communication apartments) {
        this.apartments = apartments;
    }

    public void addPlayer(Creature creature) {
        characters.add(creature);
    }

    public void startGame() throws InterruptedException {
            int fatigue = 0;
            int count = 0;

            System.out.println("Началась веселая игра");
            Thread.sleep(1000);
            characters.get(0).setFirst(true);
            characters.get(characters.size() - 1).setLast(true);

            characters.get(0).setLastRoom(apartments.getRoom(0));
            System.out.println(characters.get(0).getLastRoom().getName() + " стала началом погони");
            System.out.println("Впереди " + characters.get(0).getMyAct().getAction() + " " + characters.get(0).getName());
            Thread.sleep(1000);

            for (int i = 1; i < characters.size() * 10; i++) {
                int number = i % characters.size();
                //Условие для первого игрока, так как он никого не догоняет, то distanceToTarget у него не изменяется
                if (characters.get(number).getFirst()) {
                    characters.get(number).setLastRoom(apartments.getCommunication(characters.get(0).getLastRoom()));
                    System.out.println("Погоня переместилась и ее продолжением стала " + characters.get(0).getLastRoom().getName());
                    System.out.println("Впереди все еще " + characters.get(0).getMyAct().getAction() + " " + characters.get(0).getName());
                    Thread.sleep(1000);
                }
                //действие последнего в игре и изменение расстояний
                else if (characters.get(number).getLast()) {
                    System.out.println("Последним " + characters.get(number).getMyAct().getAction() + " " + characters.get(number).getName());
                    Thread.sleep(1000);
                    characters.get(number).setDistanceToTarget(characters.get(number).getDistanceToTarget() + characters.get(number - 1).getSpeed() - characters.get(number).getSpeed());
                }
                //Два варианта предложения для игроков, которые не являются ни последними, ни перавыми. В обоих случаях расстояние изменяется
                else if (Math.random() < 0.5) {
                    System.out.println(characters.get(number).getName() + " " + characters.get(number).getMyAct().getAction() + " следом");
                    Thread.sleep(1000);
                    characters.get(number).setDistanceToTarget(characters.get(number).getDistanceToTarget() + characters.get(number - 1).getSpeed() - characters.get(number).getSpeed());
                } else {
                    System.out.println(characters.get(number).getName() + " " + characters.get(number).getMyAct().getAction() + " за ним");
                    Thread.sleep(1000);
                    characters.get(number).setDistanceToTarget(characters.get(number).getDistanceToTarget() + characters.get(number - 1).getSpeed() - characters.get(number).getSpeed());
                }
                //Собака узнает игрока только один раз
                if (characters.get(number) instanceof Dog && count < 1) {
                    for (int k = 1; k < characters.size(); k++) {
                        if (((Dog) characters.get(number)).sniff(characters.get(k))) {
                            System.out.println("Он узнал " + characters.get(k).getName() + " по запаху и думал, что началась веселая игра.");
                        }
                    }
                    count += 1;
                }
                //Конец игры если преследователь догнал цель
                if (characters.get(number).getDistanceToTarget() <= 0) {
                    System.out.println(characters.get(number).getName() + " догнал " + characters.get(number - 1).getName() + " и игра закончилась");
                    fatigue = -1;
                    break;
                }
                //System.out.println();
            }

            if (fatigue > -1) {
                System.out.println("Игра закончилась потому что игроки устали");
            }
    }
}
