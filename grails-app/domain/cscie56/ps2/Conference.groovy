package cscie56.ps2

class Conference {

    static belongsTo = [league:League]
    static hasMany = [teams:Team]

    static constraints = {
        teams minSize: 1
    }
}
