(() => {
  if (window.__ytMediaTvRemote) return;
  window.__ytMediaTvRemote = true;

  const keys = {
    ArrowUp: 38,
    ArrowDown: 40,
    ArrowLeft: 37,
    ArrowRight: 39,
    Enter: 13
  };

  window.addEventListener("keydown", e => {
    if (!(e.key in keys)) return;

    const target = document.activeElement || document.body;

    target.dispatchEvent(new KeyboardEvent("keypress", {
      key: e.key,
      code: e.key,
      keyCode: keys[e.key],
      which: keys[e.key],
      bubbles: true,
      cancelable: true
    }));
  }, true);
})();
