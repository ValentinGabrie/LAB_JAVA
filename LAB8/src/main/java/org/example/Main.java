package org.example;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ExceptieVarsta extends Exception {
    public ExceptieVarsta(String message) {
        super(message);
    }
}

class ExceptieAnExcursie extends Exception {
    public ExceptieAnExcursie(String message) {
        super(message);
    }
}
public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root"; // Schimbați dacă aveți alt utilizator
    private static final String DB_PASSWORD = "1111"; // Schimbați dacă aveți o parolă

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexiune reușită la baza de date!");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nMeniu:");
                System.out.println("1. Adăugare persoană");
                System.out.println("2. Adăugare excursie");
                System.out.println("3. Afișare persoane și excursii");
                System.out.println("4. Afișare excursii pentru o persoană");
                System.out.println("5. Persoane care au vizitat o destinație");
                System.out.println("6. Persoane care au făcut excursii într-un an");
                System.out.println("7. Ștergere excursie");
                System.out.println("8. Ștergere persoană");
                System.out.println("9. Ieșire");
                System.out.print("Alegeți o opțiune: ");
                int optiune = scanner.nextInt();

                switch (optiune) {
                    case 1 -> adaugaPersoana(scanner);
                    case 2 -> adaugaExcursie(scanner);
                    case 3 -> afiseazaPersoaneSiExcursii();
                    case 4 -> afiseazaExcursiiPentruPersoana(scanner);
                    case 5 -> afiseazaPersoanePentruDestinatie(scanner);
                    case 6 -> afiseazaPersoanePentruAn(scanner);
                    case 7 -> stergeExcursie(scanner);
                    case 8 -> stergePersoana(scanner);
                    case 9 -> {
                        System.out.println("Ieșire din aplicație.");
                        return;
                    }
                    default -> System.out.println("Opțiune invalidă. Încercați din nou.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adaugaPersoana(Scanner scanner) {
        try {
            System.out.print("Introduceți numele: ");
            String nume = scanner.next();

            System.out.print("Introduceți vârsta: ");
            int varsta = scanner.nextInt();

            if (varsta <= 0 || varsta > 120) {
                throw new ExceptieVarsta("Vârsta trebuie să fie între 1 și 120.");
            }

            String sql = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, nume);
                stmt.setInt(2, varsta);
                stmt.executeUpdate();
                System.out.println("Persoana a fost adăugată cu succes.");
            }
        } catch (ExceptieVarsta e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adaugaExcursie(Scanner scanner) {
        try {
            System.out.print("Introduceți ID-ul persoanei: ");
            int idPersoana = scanner.nextInt();

            System.out.print("Introduceți destinația: ");
            String destinatia = scanner.next();

            System.out.print("Introduceți anul excursiei: ");
            int anul = scanner.nextInt();

            // Verificare dacă persoana există
            String verificareSQL = "SELECT varsta FROM persoane WHERE id = ?";
            try (PreparedStatement verificareStmt = connection.prepareStatement(verificareSQL)) {
                verificareStmt.setInt(1, idPersoana);
                ResultSet rs = verificareStmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("Persoana cu acest ID nu există.");
                    return;
                }

                int varsta = rs.getInt("varsta");
                int anNastere = 2023 - varsta;

                if (anul < anNastere || anul > 2023) {
                    throw new ExceptieAnExcursie("Anul excursiei trebuie să fie între anul nașterii și 2023.");
                }
            }

            // Adăugare excursie
            String sql = "INSERT INTO excursii (id_persoana, destinatia, anul) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idPersoana);
                stmt.setString(2, destinatia);
                stmt.setInt(3, anul);
                stmt.executeUpdate();
                System.out.println("Excursia a fost adăugată cu succes.");
            }
        } catch (ExceptieAnExcursie e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaPersoaneSiExcursii() {
        String sql = "SELECT p.id, p.nume, p.varsta, e.destinatie, e.anul " +
                "FROM persoane p LEFT JOIN excursii e ON p.id = e.id_persoana";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Map<Integer, String> persoane = new HashMap<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                String destinatia = rs.getString("destinati");
                int anul = rs.getInt("anul");

                persoane.putIfAbsent(id, nume + " (" + varsta + " ani)");
                System.out.println(persoane.get(id) + " -> " +
                        (destinatia == null ? "Fără excursii" : destinatia + " (" + anul + ")"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void afiseazaExcursiiPentruPersoana(Scanner scanner) {
        try {
            System.out.print("Introduceți numele persoanei: ");
            String nume = scanner.next();

            String sql = "SELECT e.destinatie, e.anul FROM excursii e " +
                    "JOIN persoane p ON e.id_persoana = p.id WHERE p.nume = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, nume);
                ResultSet rs = stmt.executeQuery();

                System.out.println("Excursiile persoanei " + nume + ":");
                boolean existaExcursii = false;
                while (rs.next()) {
                    existaExcursii = true;
                    String destinatia = rs.getString("destinatie");
                    int anul = rs.getInt("anul");
                    System.out.println("- " + destinatia + " (" + anul + ")");
                }

                if (!existaExcursii) {
                    System.out.println("Nu există excursii pentru această persoană.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaPersoanePentruDestinatie(Scanner scanner) {
        try {
            System.out.print("Introduceți destinația: ");
            String destinatia = scanner.next();

            String sql = "SELECT DISTINCT p.nume, p.varsta FROM persoane p " +
                    "JOIN excursii e ON p.id = e.id_persoana WHERE e.destinatie = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, destinatia);
                ResultSet rs = stmt.executeQuery();

                System.out.println("Persoanele care au vizitat " + destinatia + ":");
                boolean existaPersoane = false;
                while (rs.next()) {
                    existaPersoane = true;
                    String nume = rs.getString("nume");
                    int varsta = rs.getInt("varsta");
                    System.out.println("- " + nume + " (" + varsta + " ani)");
                }

                if (!existaPersoane) {
                    System.out.println("Nicio persoană nu a vizitat această destinație.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void afiseazaPersoanePentruAn(Scanner scanner) {
        try {
            System.out.print("Introduceți anul: ");
            int anul = scanner.nextInt();

            String sql = "SELECT DISTINCT p.nume, p.varsta FROM persoane p " +
                    "JOIN excursii e ON p.id = e.id_persoana WHERE e.anul = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, anul);
                ResultSet rs = stmt.executeQuery();

                System.out.println("Persoanele care au făcut excursii în anul " + anul + ":");
                boolean existaPersoane = false;
                while (rs.next()) {
                    existaPersoane = true;
                    String nume = rs.getString("nume");
                    int varsta = rs.getInt("varsta");
                    System.out.println("- " + nume + " (" + varsta + " ani)");
                }

                if (!existaPersoane) {
                    System.out.println("Nicio persoană nu a făcut excursii în acest an.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void stergeExcursie(Scanner scanner) {
        try {
            System.out.print("Introduceți ID-ul excursiei de șters: ");
            int idExcursie = scanner.nextInt();

            String sql = "DELETE FROM excursii WHERE id_excursie = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idExcursie);
                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Excursia a fost ștearsă cu succes.");
                } else {
                    System.out.println("Nu există nicio excursie cu acest ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void stergePersoana(Scanner scanner) {
        try {
            System.out.print("Introduceți ID-ul persoanei de șters: ");
            int idPersoana = scanner.nextInt();

            String sql = "DELETE FROM persoane WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idPersoana);
                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Persoana și excursiile asociate au fost șterse cu succes.");
                } else {
                    System.out.println("Nu există nicio persoană cu acest ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
        

    }


}