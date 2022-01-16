const puppeteer = require('puppeteer');
const atob = require('atob');
const btoa = require('btoa');
fs = require('fs');

  
  


(async function main(){
  const browser = await puppeteer.launch({
    headless:false, 
    defaultViewport:null,
    devtools: false,
    args: ['--window-size=1920,1170','--window-position=0,0']
  });

  const page = (await browser.pages())[0];

  const client = await page.target().createCDPSession();
  await client.send('Network.enable');


const scriptUrlPatterns = [
  '*'
]
await page.goto('https://www.instagram.com/matte0z');
await client.send('Network.setRequestInterception', { 
  patterns: scriptUrlPatterns.map(pattern => ({
    urlPattern: pattern, resourceType: 'XHR', interceptionStage: 'HeadersReceived'
  }))
});



client.on('Network.requestIntercepted', async ({ interceptionId, request, responseHeaders, resourceType }) => {
  

  const response = await client.send('Network.getResponseBodyForInterception',{ interceptionId });
  const bodyData = response.base64Encoded ? atob(response.body) : response.body;

  console.log(`Intercepted ${request.url} {interception id: ${interceptionId}}`);
 
  fs.appendFile('a.txt', "\n&A4d"+bodyData+"&9D!", function (err) {
    if (err) return console.log(err);
    console.log('Logging');
  });


  client.send('Network.continueInterceptedRequest', {
    interceptionId,
  });

  
  
});





})()
