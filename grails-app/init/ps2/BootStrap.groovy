package ps2

import cscie56.ps2.Conference
import cscie56.ps2.Game
import cscie56.ps2.League
import cscie56.ps2.Season
import cscie56.ps2.Team

class BootStrap {

    def init = { servletContext ->
        League league = new League()

        Conference eastern = new Conference()
        eastern.league = league

        Conference western = new Conference()
        western.league = league


        List<Team> teams = []
        Team clevelandCavaliers = new Team(name: 'Cleveland Cavaliers')
        clevelandCavaliers.conference = eastern
        eastern.teams = [] << clevelandCavaliers
        teams << clevelandCavaliers
        //clevelandCavaliers.save()

        Team bostonCeltics = new Team(name: 'Boston Celtics')
        bostonCeltics.conference = eastern
        teams << bostonCeltics
        eastern.teams << bostonCeltics
        //bostonCeltics.save()

        Team washingtonWizards = new Team(name: 'Washington Wizards')
        washingtonWizards.conference = eastern
        eastern.teams << washingtonWizards
        teams << washingtonWizards
        //washingtonWizards.save()

        Team torontoRaptors = new Team(name: 'Toronto Raptors')
        torontoRaptors.conference = eastern
        eastern.teams << torontoRaptors
        teams << torontoRaptors
        //torontoRaptors.save()

        Team californiaStateWarriors = new Team(name: 'California State Warriors')
        californiaStateWarriors.conference = western
        western.teams = [] << californiaStateWarriors
        teams << californiaStateWarriors
        //californiaStateWarriors.save()

        Team sanAntoniaSpurs = new Team(name: 'San Antonio Spurs')
        sanAntoniaSpurs.conference = western
        western.teams << sanAntoniaSpurs
        teams << sanAntoniaSpurs
        //sanAntoniaSpurs.save()

        Team houstonRockets = new Team(name: 'Houston Rockets')
        houstonRockets.conference = western
        western.teams << houstonRockets
        teams << houstonRockets
        //houstonRockets.save()

        Team utahJazz = new Team(name: 'Utah Jazz')
        utahJazz.conference = western
        western.teams << utahJazz
        teams << utahJazz
        //utahJazz.save()


        //eastern.save()
        //western.save()
        league.conferences = [] << eastern << western


        Season season = new Season(name: '2017', startDate: new Date(2017, 1, 1), endDate: new Date(2017, 2, 12))
        season.league = league
        league.seasons = [] << season
        //season.save()
        for(int i = 0; i < teams.size(); ++i) {
            for(int j = 0; j < teams.size(); ++j) {
                if(i == j)
                    continue;

                Game game = new Game(homeTeam: teams[i], awayTeam: teams[j], date: new Date(), location: "")
                game.generateOutput()
                game.season = season
                //season.games << game
                game.save()
            }
        }
        league.save()
    }
    def destroy = {
    }
}
