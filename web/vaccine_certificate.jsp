<html>
    
    <head>
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
<style>
    

.cursive {
  font-family: 'Pinyon Script', cursive;
}

.sans {
  font-family: 'Open Sans', sans-serif;
}

.bold {
  font-weight: bold;
}

.block {
  display: block;
}

.underline {
  border-bottom: 1px solid #777;
  padding: 5px;
  margin-bottom: 15px;
}

.margin-0 {
  margin: 0;
}

.padding-0 {
  padding: 0;
}

.pm-empty-space {
  height: 40px;
  width: 100%;
}

body {
  padding: 20px 0;
  background: #ccc;
}

.pm-certificate-container {
  position: relative;
  width: 800px;
  height: 600px;
  background-color: #618597;
  padding: 30px;
  color: #333;
  font-family: 'Open Sans', sans-serif;
  box-shadow: 0 0 5px rgba(0, 0, 0, .5);
  /*background: -webkit-repeating-linear-gradient(
    45deg,
    #618597,
    #618597 1px,
    #b2cad6 1px,
    #b2cad6 2px
  );
  background: repeating-linear-gradient(
    90deg,
    #618597,
    #618597 1px,
    #b2cad6 1px,
    #b2cad6 2px
  );*/
  
  .outer-border {
    width: 794px;
    height: 594px;
    position: absolute;
    left: 50%;
    margin-left: -397px;
    top: 50%;
    margin-top:-297px;
    border: 2px solid #fff;
  }
  
  .inner-border {
    width: 730px;
    height: 530px;
    position: absolute;
    left: 50%;
    margin-left: -365px;
    top: 50%;
    margin-top:-265px;
    border: 2px solid #fff;
  }

  .pm-certificate-border {
    position: relative;
    width: 720px;
    height: 520px;
    padding: 0;
    border: 1px solid #E1E5F0;
    background-color: rgba(255, 255, 255, 1);
    background-image: none;
    left: 50%;
    margin-left: -360px;
    top: 50%;
    margin-top: -260px;

    .pm-certificate-block {
      width: 650px;
      height: 200px;
      position: relative;
      left: 50%;
      margin-left: -325px;
      top: 70px;
      margin-top: 0;
    }

    .pm-certificate-header {
      margin-bottom: 10px;
    }

    .pm-certificate-title {
      position: relative;
      top: 40px;

      h2 {
        font-size: 34px !important;
      }
    }

    .pm-certificate-body {
      padding: 20px;

      .pm-name-text {
        font-size: 20px;
      }
    }

    .pm-earned {
      margin: 15px 0 20px;
      .pm-earned-text {
        font-size: 20px;
      }
      .pm-credits-text {
        font-size: 15px;
      }
    }

    .pm-course-title {
      .pm-earned-text {
        font-size: 20px;
      }
      .pm-credits-text {
        font-size: 15px;
      }
    }

    .pm-certified {
      font-size: 12px;

      .underline {
        margin-bottom: 5px;
      }
    }

    .pm-certificate-footer {
      width: 650px;
      height: 100px;
      position: relative;
      left: 50%;
      margin-left: -325px;
      bottom: -105px;
    }
  }
}

</style>
    </head>
 <%
            String q=request.getParameter("data");
            System.out.println(">>>>>"+q);
            String a[]=q.split("-");
            String fname=a[0];
            String lname=a[1];
            String email=a[2];
            String mobile=a[3];
            String gender=a[4];
           
        
            %>

<body>
  <div class="container pm-certificate-container">
    <div class="outer-border"></div>
    <div class="inner-border"></div>
    
    <div class="pm-certificate-border col-xs-12">
      <div class="row pm-certificate-header">
        <div class="pm-certificate-title cursive col-xs-12 text-center">
          <h2>Covid19 Vaccine Certificate</h2>
        </div>
      </div>

      <div class="row pm-certificate-body">
        
        <div class="pm-certificate-block">
            <div class="col-xs-12">
              <div class="row">
                <div class="col-xs-2"><!-- LEAVE EMPTY --></div>
                <div class="pm-certificate-name underline margin-0 col-xs-8 text-center">
                  <span class="pm-name-text bold">Fully Vaccinated</span>
                </div>
                <div class="col-xs-2"><!-- LEAVE EMPTY --></div>
              </div>
            </div>          

            <div class="col-xs-12">
              <div class="row">
                <div class="col-xs-2"><!-- LEAVE EMPTY --></div>
                <div class="pm-earned col-xs-8 text-center">
                  <span class="pm-earned-text padding-0 block cursive">has earned</span>
                  <span class="pm-credits-text block bold sans">PD175: 1.0 Credit Hours</span>
                </div>
                <div class="col-xs-2"><!-- LEAVE EMPTY --></div>
                <div class="col-xs-12"></div>
              </div>
            </div>
            
            <div class="col-xs-12">
              <div class="row">
                <div class="col-xs-2"><!-- LEAVE EMPTY --></div>
                <div class="pm-course-title col-xs-8 text-center">
                  <span class="pm-earned-text block cursive">while completing the training course entitled</span>
                </div>
                <div class="col-xs-2"><!-- LEAVE EMPTY --></div>
              </div>
            </div>

            <div class="col-xs-12">
              <div class="row">
                <div class="col-xs-2"><!-- LEAVE EMPTY --></div>
                <div class="pm-course-title underline col-xs-8 text-center">
                  <span class="pm-credits-text block bold sans">BPS PGS Initial PLO for Principals at Cluster Meetings</span>
                </div>
                <div class="col-xs-2"><!-- LEAVE EMPTY --></div>
              </div>
            </div>
        </div>       
        
        <div class="col-xs-12">
          <div class="row">
            <div class="pm-certificate-footer">
                <div class="col-xs-4 pm-certified col-xs-4 text-center">
                  <span class="pm-credits-text block sans">Buffalo City School District</span>
                  <span class="pm-empty-space block underline"></span>
                  <span class="bold block">Crystal Benton Instructional Specialist II, Staff Development</span>
                </div>
                <div class="col-xs-4">
                  <!-- LEAVE EMPTY -->
                </div>
                <div class="col-xs-4 pm-certified col-xs-4 text-center">
                  <span class="pm-credits-text block sans">Date Completed</span>
                  <span class="pm-empty-space block underline"></span>
                  <span class="bold block">DOB: </span>
                  <span class="bold block">Social Security # (last 4 digits)</span>
                </div>
            </div>
          </div>
        </div>

      </div>

    </div>
  </div>
</body>
</html>