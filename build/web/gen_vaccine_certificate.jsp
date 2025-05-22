

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vaccine Record</title>
        <script src='JS/jspdf.min.js'></script>
        <script>
function myFunction() {
 
  window.print();
}
var base64Img = null;
imgToBase64('octocat.jpg', function(base64) {
    base64Img = base64; 
});

margins = {
  top: 70,
  bottom: 40,
  left: 30,
  width: 550
};
generate = function()
{
	var pdf = new jsPDF('p', 'pt', 'a4');
	pdf.setFontSize(18);
	pdf.fromHTML(document.getElementById('html-2-pdfwrapper'), 
		margins.left, // x coord
		margins.top,
		{
			// y coord
			width: margins.width// max width of content on PDF
		},function(dispose) {
			headerFooterFormatting(pdf, pdf.internal.getNumberOfPages());
		}, 
		margins);
		
	var iframe = document.createElement('iframe');
	iframe.setAttribute('style','position:absolute;right:0; top:0; bottom:0; height:100%; width:650px; padding:20px;');
	document.body.appendChild(iframe);
	
	iframe.src = pdf.output('datauristring');
};
function headerFooterFormatting(doc, totalPages)
{
    for(var i = totalPages; i >= 1; i--)
    {
        doc.setPage(i);                            
        //header
        header(doc);
        
        footer(doc, i, totalPages);
        doc.page++;
    }
};
function header(doc)
{
    doc.setFontSize(30);
    doc.setTextColor(40);
    doc.setFontStyle('normal');
	
    if (base64Img) {
       doc.addImage(base64Img, 'JPEG', margins.left, 10, 40,40);        
    }
	    
    doc.text("Vaccine Certificate", margins.left + 50, 40 );
	doc.setLineCap(2);
	doc.line(3, 70, margins.width + 43,70); // horizontal line
};
function imgToBase64(url, callback, imgVariable) {
 
    if (!window.FileReader) {
        callback(null);
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.responseType = 'blob';
    xhr.onload = function() {
        var reader = new FileReader();
        reader.onloadend = function() {
			imgVariable = reader.result.replace('text/xml', 'image/jpeg');
            callback(imgVariable);
        };
        reader.readAsDataURL(xhr.response);
    };
    xhr.open('GET', url);
    xhr.send();
};

function footer(doc, pageNumber, totalPages){

    var str = "Page " + pageNumber + " of " + totalPages
   
    doc.setFontSize(10);
    doc.text(str, margins.left, doc.internal.pageSize.height - 20);
    
};
</script>
    </head>
    <body>
        <%
            String name=request.getParameter("name");
            String aadhar=request.getParameter("aadhar");
            String dob=request.getParameter("dob");
            String mobile=request.getParameter("mobile");
            String email=request.getParameter("email");
            String city=request.getParameter("city");
            String gender=request.getParameter("gender");
            String status=request.getParameter("status");
            String vaccine_details=request.getParameter("vaccine_details");
//            System.out.println(">>>>>"+q);
//            String a[]=q.split("-");
//            String name=a[0];
//            String aadhar=a[1];
//            String dob=a[2];
//            String mobile=a[3];
//            String email=a[4];
//            String city=a[5];
//            String gender=a[6];
//            String status=a[7];
//            String vaccine_details=a[8];
           System.out.println(name);
           System.out.println(aadhar);
           System.out.println(dob);
           System.out.println(mobile);
           System.out.println(email);
           System.out.println(city);
           System.out.println(gender);
           System.out.println(status);
           System.out.println(vaccine_details);
        
            %>
            <div id="html-2-pdfwrapper" style='position: absolute; left: 20px; top: 50px; bottom: 0; overflow: auto; width: 600px'>

     
        
        <font color="Red" size="10">Fully Vaccinated</font>
        </br>
        Name: <%=name%> </br>
        Aadhar: <%=aadhar%> <br>
        DOB: <%=dob%> <br>
        
        
        Mobile <%=mobile%> <br>
        Email <%=email%> <br>
        City <%=city%> <br>
        Gender <%=gender%> <br>
        Status <%=status%> <br>
        Vaccine Details <%=vaccine_details%> <br>
       
        <%
            String flie=mobile+".png";
           
            %>
            <img src="<%=flie%>" width="300" height="300"/>
            </br>
            
         <button onclick="myFunction()">Save </button>
<!--         <button onclick="generate()">Save1 </button>-->
            </div>
    </body>
</html>
