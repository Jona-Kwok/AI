#!/usr/bin/env python 

import os, sys
from distutils.core import setup, Extension

# adjust the following two variables to customize your build
extra_compile_args = []
extra_link_args = []

MATHSAT_DIR = '..'

libraries = ['mathsat', 'gmpxx', 'gmp']
if sys.platform == 'win32':
    libraries += ['psapi']

setup(name='mathsat', version='0.1',
      description='MathSAT API',
      ext_modules=[Extension('_mathsat', ['mathsat_python_wrap.c'],
                             define_macros=[('SWIG','1')],
                             include_dirs=[os.path.join(MATHSAT_DIR,
                                                        'include')],
                             library_dirs=[os.path.join(MATHSAT_DIR, 'lib')],
                             extra_compile_args=extra_compile_args,
                             extra_link_args=extra_link_args,
                             libraries=libraries,
                             language='c++',
                             )]
      )
