package cscie56.ps2

class Game {

    Team homeTeam
    Team awayTeam
    Date date
    String location

    static belongsTo = [season:Season]

    static constraints = {
    }

    def generateOutput() {

        //generating scores
        Random rand = new Random()
        int firstScore = rand.nextInt(10)
        int secondScore = rand.nextInt(10)

        //updating team classes variables
        if(firstScore > secondScore) {
            ++homeTeam.wins
            if(homeTeam.streakStr == "W")
                ++homeTeam.streakCount
            else
                homeTeam.streakCount = 1
            homeTeam.streakStr = "W"
            ++awayTeam.losses
            if(awayTeam.streakStr == "L")
                ++awayTeam.streakCount
            else
                awayTeam.streakCount = 1
            awayTeam.streakStr = "L"
        }
        else if(firstScore < secondScore) {
            ++homeTeam.losses
            if(homeTeam.streakStr == "L")
                ++homeTeam.streakCount
            else
                homeTeam.streakCount = 1
            homeTeam.streakStr = "L"
            ++awayTeam.wins
            if(awayTeam.streakStr == "W")
                ++awayTeam.streakCount
            else
                awayTeam.streakCount = 1
            awayTeam.streakStr = "W"
        }
        else {
            ++homeTeam.ties
            if(homeTeam.streakStr == "T")
                ++homeTeam.streakCount
            else
                homeTeam.streakCount = 1
            homeTeam.streakStr = "T"
            ++awayTeam.ties
            if(awayTeam.streakStr == "T")
                ++awayTeam.streakCount
            else
                awayTeam.streakCount = 1
            awayTeam.streakStr = "T"
        }
        homeTeam.scored += firstScore
        awayTeam.scored += secondScore
        homeTeam.allowed += secondScore
        awayTeam.allowed += firstScore
    }
}
