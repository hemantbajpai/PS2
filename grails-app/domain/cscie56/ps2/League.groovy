package cscie56.ps2

class League {

    static hasMany = [conferences: Conference, seasons:Season]

    static constraints = {
        conferences minSize: 2
    }
}
