<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>

<div class="container">

    <g:set var="result" value="${results}"/>

    <div class="row">

        <div class="col-lg-2 col-md-2 col-sm-3 col-xs-4">
            <div> Team </div>
            <g:each var="result" in="${results}">
                <div>${result.key.name}</div>
            </g:each>
        </div>

        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">
            <div> Wins </div>
            <g:each var="result" in="${results}">
                <div>${result.value[0]}</div>
            </g:each>
        </div>

        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">
            <div> Losses </div>
            <g:each var="result" in="${results}">
                <div>${result.value[1]}</div>
            </g:each>
        </div>


    </div>

</div>