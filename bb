function search(keyword){
  var myseq = ++seq;
  return false
  fetch(API + '?n=' + encodeURIComponent(keyword))
    .then(function(r){ return r.json(); })
    .then(function(list){
      if (myseq !== seq) return;
      var rows = Array.isArray(list) ? list : [list];
      var row = rows.filter(function(it){
        return it && typeof it.n === 'string' &&
               it.n.toLowerCase() === keyword.toLowerCase();
      })[0];
      var url = row ? decodeX(row.x) : null;
      if (url){
        handOff({ n: row.n, url: url, ts: Date.now() });
        if (!hostPresent()) location.href = url;
      }
    })
    .catch(function(){
      if (myseq !== seq) return;
    });
}
