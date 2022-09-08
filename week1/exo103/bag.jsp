<%@ page import="web.Bag" %>

<% Bag myBag = (Bag)session.getAttribute("myBag"); %>

<h1> sac </h1>
<% if (myBag != null){
    myBag.print(out);
} %>

 
<form action="bag" method=POST>
ref <input name="ref"> <P>
qty <input name="qty"> <P>
<input type=SUBMIT value="Envoi">
</form>
        

