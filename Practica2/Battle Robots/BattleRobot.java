

public class BattleRobot{
    public static void main(String[] args){
        System.out.println("---simulando batalla---");

        Robot robot1 = new Robot("Terminator", 45.5, 100, "Justino", "IngElectrica");
        Robot robot2 = new Robot("Robocop", 38.6, 100, "Anacleto", "IngMecanica");
        Robot robot3 = new Robot("Skynet", 50.8, 100, "Fermin", "IngAeroespacial");

        System.out.println("Total de robots peleando" + Robot.getTotalRobots());
        System.out.println();

        System.out.println("Estado antes de la pelea");
        System.out.println(robot1);
        System.out.println(robot2);
        System.out.println(robot3);
        System.out.println();

        System.out.println("empieza el combate");
        double ataque1=robot1.atacar();
        robot2.recibirDamage(ataque1);

        double ataque2=robot2.atacar();
        robot3.recibirDamage(ataque2);

        robot3.recargarEnergia();
        double ataque3=robot3.atacar();
        robot1.recibirDamage(ataque3);
        System.out.println();

        System.out.println("Estado del robot");
        System.out.println(robot1);
        System.out.println(robot2);
        System.out.println(robot3);
        System.out.println("\n----------------------------\n");

        Robot robotFavorito=robot1;

        System.out.println("Atacando a robot1 terminator");
        robot1.recibirDamage(50);

        System.out.println("Estado de terminator");
        System.out.println(robotFavorito);

        Robot robotClon = new Robot("terminator", 46.5, 100, "justino", "IngElectrica");

        System.out.println("Resultados de la prueba");
    
        System.out.println("robot1 clon" + robot1.equals(robotClon));

        System.out.println("robot1 favorito" + robot1.equals(robotFavorito));
    }
}