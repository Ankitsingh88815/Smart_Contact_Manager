console.log("Script is loaded");

document.addEventListener("DOMContentLoaded", () => {
  let currentTheme = getTheme();

  // Apply the theme to the page
  changePageTheme(currentTheme, currentTheme);

  // Set up the button listener
  const changeThemeButton = document.querySelector('#theme-toggle');

  if (changeThemeButton) {
    changeThemeButton.addEventListener("click", () => {
      console.log("Change theme button clicked");

      let oldTheme = currentTheme;
      currentTheme = (currentTheme === "light") ? "dark" : "light";

      changePageTheme(currentTheme, oldTheme);
    });
  }
});

function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

function getTheme() {
  const theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

function changePageTheme(theme, oldTheme) {
  setTheme(theme);

  const html = document.querySelector("html");
  html.classList.remove(oldTheme);
  html.classList.add(theme);

  const themeToggleSpan = document.querySelector('#theme-toggle span');
  if (themeToggleSpan) {
    themeToggleSpan.textContent = (theme === "light") ? "Dark" : "Light";
  }
}
