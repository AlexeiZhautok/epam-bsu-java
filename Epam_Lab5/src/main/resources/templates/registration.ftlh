<#import "parts/main.ftlh" as m>
<@m.page>
    <div class="container login-container" align="center">
        <div class="row" >
            <div class="col-md-6 login-form-1">
                <h3>Add new User</h3>
                <h4 style="color: orange">${message?if_exists}</h4>
                <form action="/registration" method="post">
                    <div class="form-group">
                        <input type="text" name="username" class="form-control" placeholder=" Your Username *" value="" />
                    </div>
                    <div class="form-group">
                        <input type="password"  name="password" class="form-control" placeholder="Password" value="" />
                    </div>
                    <div class="form-group">
                        <input type="password"  name="passwordSecond" class="form-control" placeholder="Confirm password" value="" />
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btnSubmit" value="Login" />
                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </form>
            </div>
        </div>
    </div>
</@m.page>

<style>
    .login-container{
        margin-top: 5%;
        margin-bottom: 5%;
        margin-left: 24%;
    }
    .login-form-1{
        padding: 5%;
        box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
    }
    .login-form-1 h3{
        text-align: center;
        color: #333;
    }
    .login-form-2{
        padding: 5%;
        background: #0062cc;
        box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
    }
    .login-form-2 h3{
        text-align: center;
        color: #fff;
    }
    .login-container form{
        padding: 10%;
    }
    .btnSubmit
    {
        width: 50%;
        border-radius: 1rem;
        padding: 1.5%;
        border: none;
        cursor: pointer;
    }
    .login-form-1 .btnSubmit{
        font-weight: 600;
        color: #fff;
        background-color: #0062cc;
    }
    .login-form-2 .btnSubmit{
        font-weight: 600;
        color: #0062cc;
        background-color: #fff;
    }
    .login-form-2 .ForgetPwd{
        color: #fff;
        font-weight: 600;
        text-decoration: none;
    }
    .login-form-1 .ForgetPwd{
        color: #0062cc;
        font-weight: 600;
        text-decoration: none;
    }
</style>
