package pl.edu.agh.mplt.web

import org.scalatra._
import scalate.ScalateSupport

class MPLTServlet extends MpltWebStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }
  
}
