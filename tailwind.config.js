module.exports = {
  darkMode: 'class',
  // purge: {
  //   preserveHtmlElements: false,
  //   enabled: true,
  //   content: ['./src/**/*.cljs'],
  //   extract: {
  //     md: (content) => {
  //       return content.match(/[^<>"'`:\s]*/)
  //     }
  //   }
  // },
  theme: {
    fontSize: {
      xxs: [
        '10px',
        {
          lineHeight: '14px',
          letterSpacing: '1px'
        }
      ],
      xs: [
        '12px',
        {
          lineHeight: '16px',
          letterSpacing: '0.2px'
        }
      ],
      sm: [
        '14px',
        {
          lineHeight: '20px',
          letterSpacing: '0.1px'
        }
      ],
      base: [
        '16px',
        {
          lineHeight: '24px',
          letterSpacing: '0px'
        }
      ],
      lg: [
        '18px',
        {
          lineHeight: '28px',
          letterSpacing: '0px'
        }
      ],
      xl: [
        '20px',
        {
          lineHeight: '28px',
          letterSpacing: '0px'
        }
      ],
      '2xl': [
        '24px',
        {
          lineHeight: '32px',
          letterSpacing: '0px'
        }
      ],
      '3xl': [
        '30px',
        {
          lineHeight: '36px',
          letterSpacing: '0px'
        }
      ],
      '4xl': [
        '36px',
        {
          lineHeight: '40px',
          letterSpacing: '0px'
        }
      ],
      '5xl': [
        '48px',
        {
          lineHeight: '48px',
          letterSpacing: '0px'
        }
      ],
      '6xl': [
        '54px',
        {
          lineHeight: '54px',
          letterSpacing: '0px'
        }
      ],
      '7xl': [
        '72px',
        {
          lineHeight: '72px',
          letterSpacing: '-1px'
        }
      ],
      '8xl': [
        '96px',
        {
          lineHeight: '96px',
          letterSpacing: '-1.5px'
        }
      ]
    },
    extend: {
      dropShadow: {
        'text-contrast': `drop-shadow(0 0px 3px rgba(255, 255, 255, 0.07))
         drop-shadow(0 2px 2px rgba(255, 255, 255, 0.06))`
      },
      height: {
        7.5: '1.875rem'
      },
      maxWidth: {
        '1/4': '25%',
        '1/2': '50%',
        '3/4': '75%',
        65: '17rem'
      },
      backgroundImage: () => ({
        'gradient-figma': 'linear-gradient(42.33deg, var(--tw-gradient-from) 0%, var(--tw-gradient-to) 42.79%.35%)'
      }),
      backgroundPosition: {
        'position-invisible': '-100%'
      },
      spacing: {
        4.5: '1.125rem',
        5.5: '1.375rem',
        'attach-avatar-icon': '43.75%'
      },
      colors: {
        primary: {
          DEFAULT: '#57838D',
          400: '#EB202B',
          300: '#EB202B99',
          200: '#EB202B4D',
          50: '#EB202B0A'
        },
        secondary: '#231F20',
        setting: '#FCFCFE',
        grey: {
          DEFAULT: '#FCFCFE',
          10: '#FCFCFE',
          30: '#F9FAFC',
          50: '#F4F5F9',
          100: '#E9ECF2',
          200: '#D9DADD',
          300: '#C7C7C9',
          500: '#99999A',
          700: '#5C5C5C',
          900: '#312E30'
        },
        info: {
          DEFAULT: '#3E60DB',
          400: '#3E60DB',
          300: '#4C84F0',
          200: '#93C5FD',
          100: '#EAECF1',
          50: '#EFF6FF',
          // temporary color code
          'filter-label': '#D0DEEA'
        },
        warning: {
          DEFAULT: '#E27221',
          400: '#E27221',
          300: '#F59E0B',
          200: '#FCD34D',
          50: '#FFFBEB'
        },
        success: {
          DEFAULT: '#1C9B47',
          400: '#1C9B47',
          300: '#49BD71',
          200: '#81E190',
          50: '#ECFDF2'
        },
        danger: {
          DEFAULT: '#DA3F3F',
          400: '#DA3F3F',
          300: '#F43F55',
          200: '#FDA4AF',
          50: '#FFF1F2'
        },
        facebook: {
          DEFAULT: '#395185'
        }
      }
    }
  },
  variants: {
    extend: {
      transitionProperty: ['hover', 'focus', 'focus-visible'],
      divideColor: ['group-hover', 'group-focus'],
      backgroundColor: ['checked', 'group-hover', 'group-focus', 'active'],
      borderColor: ['checked', 'active', 'focus-visible'],
      textColor: ['checked', 'group-focus'],
      ringColor: ['focus', 'active', 'focus-visible', 'checked'],
      outline: ['hover', 'focus', 'active', 'focus-visible'],
      ringWidth: ['hover', 'active'],
      ringOffsetWidth: ['hover', 'active']
    }
  },
}
