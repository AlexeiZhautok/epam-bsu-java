<#macro logout>
    <div>
        <form action = "/logout" method = "post" class="form-inline my-2 my-lg-0" style="align-content: end">
            <button class="btn btn-light" type = "submit" value = "Sign Out">Sign Out</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
    </div>
</#macro>