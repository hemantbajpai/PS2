package cscie56.ps2

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SeasonController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def showStandings(Season season) {
        def teamList = []
        season.league.conferences.each { conference ->
            conference.teams.each { team ->
                teamList.add(team)
            }
        }

        def resultMap = [:]
        teamList.each{ team ->
            def values = []
            values.add(team.wins)
            values.add(team.losses)
            resultMap.put( team, values)
        }

        def eastResultsMap = [:]
        def westResultsMap = [:]
        resultMap.eachWithIndex { entry, index ->

            if (index > 3) {
                westResultsMap.put(entry.key, entry.value)
            } else {
                eastResultsMap.put(entry.key, entry.value)
            }
        }
        //respond  model:[teams: season.league.conferences[0].teams]
        render view: 'showStandings', model:[results:resultMap, westResults:westResultsMap, eastResults: eastResultsMap]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Season.list(params), model:[seasonCount: Season.count()]
    }

    def show(Season season) {
        respond season
    }

    def create() {
        respond new Season(params)
    }

    @Transactional
    def save(Season season) {
        if (season == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (season.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond season.errors, view:'create'
            return
        }

        season.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'season.label', default: 'Season'), season.id])
                redirect season
            }
            '*' { respond season, [status: CREATED] }
        }
    }

    def edit(Season season) {
        respond season
    }

    @Transactional
    def update(Season season) {
        if (season == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (season.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond season.errors, view:'edit'
            return
        }

        season.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'season.label', default: 'Season'), season.id])
                redirect season
            }
            '*'{ respond season, [status: OK] }
        }
    }

    @Transactional
    def delete(Season season) {

        if (season == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        season.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'season.label', default: 'Season'), season.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'season.label', default: 'Season'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
