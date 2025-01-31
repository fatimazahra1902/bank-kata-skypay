package main.java.com.skypay.bankkata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Account {
    private final List<Transaction> transactions = new ArrayList<>();

    public void deposit(int amount, String date) {
        transactions.add(new Transaction(LocalDate.parse(formatDate(date)), amount));
    }

    public void withdraw(int amount, String date) {
        transactions.add(new Transaction(LocalDate.parse(formatDate(date)), -amount));
    }

    public void printStatement() {
        System.out.println("Date       | Amount | Balance");
        int balance = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Trier les transactions du plus ancien au plus récent
        transactions.sort(Comparator.comparing(Transaction::getDate));

        // Calculer le solde cumulatif pour chaque transaction
        List<String> statementLines = new ArrayList<>();
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
            String formattedDate = transaction.getDate().format(formatter); // Formatage de la date
            statementLines.add(String.format("%s | %d     | %d", formattedDate, transaction.getAmount(), balance));
        }

        // Afficher dans l'ordre inverse (du plus récent au plus ancien)
        for (int i = statementLines.size() - 1; i >= 0; i--) {
            System.out.println(statementLines.get(i));
        }
    }



    private String formatDate(String date) {
        String[] parts = date.split("-");
        return parts[2] + "-" + parts[1] + "-" + parts[0];
    }
}
