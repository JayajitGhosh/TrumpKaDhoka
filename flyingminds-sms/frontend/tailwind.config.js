/**** Tailwind Config ****/
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx}',
    './components/**/*.{js,ts,jsx,tsx}',
    './app/**/*.{js,ts,jsx,tsx}'
  ],
  theme: {
    extend: {
      colors: {
        fm: {
          primary: '#6C5CE7',
          accent: '#00CEC9',
          info: '#0984E3',
          success: '#00B894',
          warning: '#FDCB6E',
          danger: '#D63031'
        }
      }
    }
  },
  plugins: []
}