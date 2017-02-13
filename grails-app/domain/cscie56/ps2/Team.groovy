package cscie56.ps2

class Team {

    String name
    int wins
    int losses
    int ties
    int scored
    int allowed
    String streakStr
    int streakCount

    static belongsTo = [conference:Conference]
    static hasMany = [players: Person, coaches:Person]

    static constraints = {
    }
}
