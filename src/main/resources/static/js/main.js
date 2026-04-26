//// Mobile navbar toggle
//document.addEventListener('DOMContentLoaded', function () {
//  const toggle = document.getElementById('navToggle');
//  const links  = document.getElementById('navLinks');
//
//  if (toggle && links) {
//    toggle.addEventListener('click', function () {
//      links.classList.toggle('open');
//    });
//  }
//
//  // Close nav when a link is clicked on mobile
//  document.querySelectorAll('.nav-links a').forEach(function (link) {
//    link.addEventListener('click', function () {
//      links.classList.remove('open');
//    });
//  });
//});
//
//
//// Animated number counter
//function animateCounters() {
//    const counters = document.querySelectorAll('.stat-number');
//    counters.forEach(function(counter) {
//        const target = parseInt(counter.getAttribute('data-target'));
//        const duration = 2000;
//        const step = target / (duration / 16);
//        let current = 0;
//
//        const timer = setInterval(function() {
//            current += step;
//            if (current >= target) {
//                counter.textContent = target.toLocaleString('en-IN');
//                clearInterval(timer);
//            } else {
//                counter.textContent = Math.floor(current).toLocaleString('en-IN');
//            }
//        }, 16);
//    });
//}
//
//// Trigger counter when stats section enters viewport
//const statsSection = document.querySelector('.stats-section');
//if (statsSection) {
//    const observer = new IntersectionObserver(function(entries) {
//        entries.forEach(function(entry) {
//            if (entry.isIntersecting) {
//                animateCounters();
//                observer.unobserve(entry.target);
//            }
//        });
//    }, { threshold: 0.3 });
//    observer.observe(statsSection);
//}


//
//document.addEventListener('DOMContentLoaded', function () {
//
//    // ── Navbar scroll effect ──────────────────────
//    const navbar = document.querySelector('.navbar');
//    if (navbar) {
//        window.addEventListener('scroll', function () {
//            if (window.scrollY > 50) {
//                navbar.classList.add('scrolled');
//            } else {
//                navbar.classList.remove('scrolled');
//            }
//        });
//    }
//
//    // ── Mobile nav toggle ─────────────────────────
//    const toggle  = document.getElementById('navToggle');
//    const links   = document.getElementById('navLinks');
//
//    if (toggle && links) {
//        toggle.addEventListener('click', function () {
//            links.classList.toggle('open');
//            toggle.classList.toggle('open');
//            document.body.style.overflow =
//                links.classList.contains('open') ? 'hidden' : '';
//        });
//
//        // Close on link click
//        links.querySelectorAll('a').forEach(function (link) {
//            link.addEventListener('click', function () {
//                links.classList.remove('open');
//                toggle.classList.remove('open');
//                document.body.style.overflow = '';
//            });
//        });
//
//        // Close on outside click
//        document.addEventListener('click', function (e) {
//            if (!navbar.contains(e.target)) {
//                links.classList.remove('open');
//                toggle.classList.remove('open');
//                document.body.style.overflow = '';
//            }
//        });
//    }
//
//    // ── VanillaTilt on product cards ──────────────
//    if (window.innerWidth > 768 && typeof VanillaTilt !== 'undefined') {
//        VanillaTilt.init(document.querySelectorAll('.product-card'), {
//            max:         8,
//            speed:       400,
//            glare:       true,
//            'max-glare': 0.15,
//            scale:       1.03,
//        });
//    }
//
//    // ── Animated counters ─────────────────────────
//    function animateCounters() {
//        document.querySelectorAll('.stat-number').forEach(function (counter) {
//            const target   = parseInt(counter.getAttribute('data-target'));
//            const duration = 2000;
//            const step     = target / (duration / 16);
//            let current    = 0;
//            const timer    = setInterval(function () {
//                current += step;
//                if (current >= target) {
//                    counter.textContent = target.toLocaleString('en-IN');
//                    clearInterval(timer);
//                } else {
//                    counter.textContent = Math.floor(current).toLocaleString('en-IN');
//                }
//            }, 16);
//        });
//    }
//
//    const statsSection = document.querySelector('.stats-section');
//    if (statsSection) {
//        const observer = new IntersectionObserver(function (entries) {
//            entries.forEach(function (entry) {
//                if (entry.isIntersecting) {
//                    animateCounters();
//                    observer.unobserve(entry.target);
//                }
//            });
//        }, { threshold: 0.3 });
//        observer.observe(statsSection);
//    }
//
//});
//
//
//
//// 3D Tilt effect on product cards
//if (window.innerWidth > 768) {
//    VanillaTilt.init(document.querySelectorAll('.product-card'), {
//        max:            8,
//        speed:          400,
//        glare:          true,
//        'max-glare':    0.15,
//        scale:          1.03,
//        perspective:    1000,
//        transition:     true,
//        axis:           null,
//        reset:          true
//    });
//}








document.addEventListener('DOMContentLoaded', function () {

    // ── Page Transition ───────────────────────────
    const transition = document.getElementById('pageTransition');

    // Fade out on load
    if (transition) {
        transition.classList.remove('active');
    }

    // Fade out on link click
    document.querySelectorAll('a[href]').forEach(function (link) {
        const href = link.getAttribute('href');
        if (
            !href.startsWith('#') &&
            !href.startsWith('http') &&
            !href.startsWith('mailto') &&
            !href.startsWith('tel') &&
            !href.startsWith('https://wa.me') &&
            link.target !== '_blank'
        ) {
            link.addEventListener('click', function (e) {
                e.preventDefault();
                if (transition) transition.classList.add('active');
                setTimeout(function () {
                    window.location.href = href;
                }, 350);
            });
        }
    });

    // ── Scroll Progress Bar ───────────────────────
    const progressBar = document.createElement('div');
    progressBar.className = 'scroll-progress';
    document.body.appendChild(progressBar);

    window.addEventListener('scroll', function () {
        const scrollTop    = window.scrollY;
        const docHeight    = document.documentElement.scrollHeight
                             - window.innerHeight;
        const scrollPercent= (scrollTop / docHeight) * 100;
        progressBar.style.width = scrollPercent + '%';
    }, { passive: true });

    // ── Back To Top Button ────────────────────────
    const backToTop = document.createElement('a');
    backToTop.className   = 'back-to-top';
    backToTop.innerHTML   = '↑';
    backToTop.href        = '#';
    backToTop.title       = 'Back to top';
    document.body.appendChild(backToTop);

    window.addEventListener('scroll', function () {
        if (window.scrollY > 400) {
            backToTop.classList.add('visible');
        } else {
            backToTop.classList.remove('visible');
        }
    }, { passive: true });

    backToTop.addEventListener('click', function (e) {
        e.preventDefault();
        window.scrollTo({ top: 0, behavior: 'smooth' });
    });

    // ── Navbar scroll effect ──────────────────────
    const navbar = document.querySelector('.navbar');
    if (navbar) {
        window.addEventListener('scroll', function () {
            navbar.classList.toggle('scrolled', window.scrollY > 50);
        }, { passive: true });
    }

    // ── Mobile nav toggle ─────────────────────────
    const toggle = document.getElementById('navToggle');
    const links  = document.getElementById('navLinks');

    if (toggle && links) {
        toggle.addEventListener('click', function () {
            links.classList.toggle('open');
            toggle.classList.toggle('open');
            document.body.style.overflow =
                links.classList.contains('open') ? 'hidden' : '';
        });

        links.querySelectorAll('a').forEach(function (link) {
            link.addEventListener('click', function () {
                links.classList.remove('open');
                toggle.classList.remove('open');
                document.body.style.overflow = '';
            });
        });

        document.addEventListener('click', function (e) {
            if (navbar && !navbar.contains(e.target)) {
                links.classList.remove('open');
                toggle.classList.remove('open');
                document.body.style.overflow = '';
            }
        });
    }

    // ── Sticky mobile inquiry button ──────────────
    const isProductDetail =
        document.querySelector('.product-detail-grid');
    if (isProductDetail) {
        const stickyBtn = document.createElement('a');
        stickyBtn.className   = 'sticky-inquiry';
        stickyBtn.href        = '/contact';
        stickyBtn.innerHTML   = '✦ &nbsp; Send Inquiry';
        document.body.appendChild(stickyBtn);
    }

    // ── VanillaTilt on product cards ──────────────
    if (window.innerWidth > 768 && typeof VanillaTilt !== 'undefined') {
        VanillaTilt.init(document.querySelectorAll('.product-card'), {
            max:         8,
            speed:       400,
            glare:       true,
            'max-glare': 0.15,
            scale:       1.03,
        });
    }

    // ── Animated counters ─────────────────────────
    function animateCounters() {
        document.querySelectorAll('.stat-number').forEach(function (counter) {
            const target   = parseInt(counter.getAttribute('data-target'));
            const step     = target / (2000 / 16);
            let   current  = 0;
            const timer    = setInterval(function () {
                current += step;
                if (current >= target) {
                    counter.textContent = target.toLocaleString('en-IN');
                    clearInterval(timer);
                } else {
                    counter.textContent =
                        Math.floor(current).toLocaleString('en-IN');
                }
            }, 16);
        });
    }

    const statsSection = document.querySelector('.stats-section');
    if (statsSection) {
        new IntersectionObserver(function (entries, obs) {
            entries.forEach(function (entry) {
                if (entry.isIntersecting) {
                    animateCounters();
                    obs.unobserve(entry.target);
                }
            });
        }, { threshold: 0.3 }).observe(statsSection);
    }

    // ── Image lazy load fade-in ───────────────────
    document.querySelectorAll('img').forEach(function (img) {
        if (img.complete) {
            img.classList.add('loaded');
        } else {
            img.addEventListener('load', function () {
                img.classList.add('loaded');
            });
        }
    });

    // ── Form submit button loading state ─────────
    const submitBtn = document.querySelector('.btn-submit');
    if (submitBtn) {
        submitBtn.closest('form') &&
        submitBtn.closest('form').addEventListener('submit', function () {
            submitBtn.innerHTML =
                '<span class="btn-text">Sending...</span>' +
                '<span class="btn-icon" style="animation:spin-icon 1s linear infinite">✦</span>';
            submitBtn.style.opacity = '0.8';
            submitBtn.style.pointerEvents = 'none';
        });
    }

    // ── Smooth scroll for anchor links ───────────
    document.querySelectorAll('a[href^="#"]').forEach(function (anchor) {
        anchor.addEventListener('click', function (e) {
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                e.preventDefault();
                target.scrollIntoView({ behavior: 'smooth', block: 'start' });
            }
        });
    });

});

// Spin icon keyframe via JS
const styleSheet = document.createElement('style');
styleSheet.textContent = `
    @keyframes spin-icon {
        from { transform: rotate(0deg);   }
        to   { transform: rotate(360deg); }
    }
`;
document.head.appendChild(styleSheet);