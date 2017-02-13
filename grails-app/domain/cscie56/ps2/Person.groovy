package cscie56.ps2

class Person {

    String firstName
    String lastName
    String role

    static belongsTo = [team:Team]

    static constraints = {
        role inList: ["coach", "player"]
    }
}
